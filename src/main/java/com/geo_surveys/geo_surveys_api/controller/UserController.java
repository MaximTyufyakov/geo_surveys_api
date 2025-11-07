package com.geo_surveys.geo_surveys_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.geo_surveys.geo_surveys_api.entity.User;
import com.geo_surveys.geo_surveys_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    public String auth(@RequestBody User user) {
        return userService.generateToken(user.login);
    }
}
