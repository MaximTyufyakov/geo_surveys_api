package com.geo_surveys.geo_surveys_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.geo_surveys.geo_surveys_api.dto.UserDto;
import com.geo_surveys.geo_surveys_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService = new UserService();

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    public String auth(@Valid @RequestBody UserDto userDto) {
        return userService.generateToken(userDto.login(), userDto.password());
    }
}
