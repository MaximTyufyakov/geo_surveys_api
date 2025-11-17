package com.geo_surveys.geo_surveys_api.config;

import com.geo_surveys.geo_surveys_api.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Request Filter that processes JWT tokens from incoming requests.
 * This filter intercepts each request to validate JWT tokens and set up Spring Security context.
 *
 * @author Your Name
 * @version 1.0
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    /**
     * Constructor for JwtRequestFilter.
     *
     * @param userService UserService instance for token validation and user details extraction
     */
    @Autowired
    public JwtRequestFilter(UserService userService) {
        this.userService = userService;
    }

    /**
     * Processes each request to validate JWT token and set up authentication context.
     *
     * @param request     HTTP servlet request
     * @param response    HTTP servlet response
     * @param filterChain Filter chain for continuing request processing
     * @throws ServletException if servlet error occurs
     * @throws IOException      if I/O error occurs
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Extract JWT token from Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final Long userId;

        // Check if Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the token from the header (remove "Bearer " prefix)
        jwtToken = authHeader.substring(7);

        try {
            // Validate token and extract user ID
            if (userService.validateToken(jwtToken)) {
                userId = userService.extractId(jwtToken);

                // Check if authentication is not already set in SecurityContext
                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Create authentication token and set it in SecurityContext
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userId, // principal
                                    null,   // credentials (null since we're using token)
                                    null    // authorities (empty for basic authentication)
                            );

                    // Add request details to authentication token
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set authentication in SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Log the exception and continue without setting authentication
            logger.error("JWT token validation failed: " + e.getMessage());
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}