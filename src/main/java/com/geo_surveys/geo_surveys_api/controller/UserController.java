package com.geo_surveys.geo_surveys_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.geo_surveys.geo_surveys_api.dto.create.TokenCreateDto;
import com.geo_surveys.geo_surveys_api.service.UserService;

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
    @ResponseStatus(HttpStatus.CREATED)
    public String auth(@Valid @RequestBody TokenCreateDto userUpdateDto) {
        return userService.generateToken(userUpdateDto.login(), userUpdateDto.password());
    }

}
