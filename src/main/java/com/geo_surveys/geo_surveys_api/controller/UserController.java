package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.geo_surveys.geo_surveys_api.dto.create.TokenCreateDto;
import com.geo_surveys.geo_surveys_api.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Map;

/**
 * Controller for work with user.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Authentication function.
     *
     * @param tokenCreateDto is user information.
     * @return token.
     */
    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@Valid @RequestBody TokenCreateDto tokenCreateDto) {
        try {
            String token = userService.auth(tokenCreateDto.login(), tokenCreateDto.password());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.ofEntries(
                    Map.entry("token", token)
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.ofEntries(
                    Map.entry("message", e.getMessage())
            ));
        }
    }

}
