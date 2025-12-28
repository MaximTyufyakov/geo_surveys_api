package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.response.MessageResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.geo_surveys.geo_surveys_api.dto.request.TokenCreateRequestDto;
import com.geo_surveys.geo_surveys_api.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Map;

/**
 * Controller for work with user.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    UserService userService;

    /**
     * Authentication function.
     *
     * @param tokenCreateRequestDto is user information.
     * @return token.
     */
    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@Valid @RequestBody TokenCreateRequestDto tokenCreateRequestDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userService.auth(
                            tokenCreateRequestDto.getLogin(),
                            tokenCreateRequestDto.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponseDto(e.getMessage()));
        }
    }

}
