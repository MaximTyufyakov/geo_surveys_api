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
     * @param userUpdateDto is user information.
     * @return token.
     */
    @PostMapping("/auth")
    public ResponseEntity<String> auth(@Valid @RequestBody TokenCreateDto userUpdateDto) {
        try{
            String token =  userService.auth(userUpdateDto.login(), userUpdateDto.password());
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

}
