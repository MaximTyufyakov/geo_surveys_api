package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.entity.User;
import com.geo_surveys.geo_surveys_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


/**
 * Service for user.
 */
@Service
public class UserService {

    /**
     * Database repository for user.
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * Secret key for jwt token.
     */
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Main authentication function.
     *
     * @param login    username.
     * @param password user password.
     * @return token.
     * @throws AuthenticationException if authentication failed.
     */
    public String auth(String login, String password) throws AuthenticationException {
        User user = userRepo.findByLogin(login);
        if (user != null && checkPassword(password, user.getPassword())) {
            return generateToken(user.getUserId());
        } else {
            throw new AuthenticationException("Неверные учетные данные");
        }
    }

    /**
     * Compare client's password with hash.
     *
     * @param plainPassword  client's password.
     * @param hashedPassword hash.
     * @return true if checked.
     */
    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

    /**
     * Generate token.
     *
     * @param user_id user id.
     * @return token.
     */
    private String generateToken(Long user_id) {
        // Key create with automatic algorithm selection.
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(user_id.toString())
                // Date of generation.
                .issuedAt(new Date())
                // Works until (10 hours).
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                // Secret key
                .signWith(key)
                .compact();
    }

    /**
     * Token validation.
     *
     * @param token client token.
     * @return true if token hasn't expired.
     */
    public boolean validateToken(String token) throws AuthenticationException {
        try {
            return !extractAllClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            throw new AuthenticationException("Неверный token");
        }
    }

    /**
     * Extract user id from token.
     *
     * @param token client token.
     * @return user id.
     */
    public Long extractId(String token) throws AuthenticationException {
        try {
            return Long.valueOf(extractAllClaims(token).getSubject());
        } catch (Exception e) {
            throw new AuthenticationException("Неверный token");
        }
    }

    /**
     * Extract all claims from token.
     *
     * @param token client token.
     * @return claims.
     */
    private Claims extractAllClaims(String token) {
        // Key create with automatic algorithm selection.
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}

