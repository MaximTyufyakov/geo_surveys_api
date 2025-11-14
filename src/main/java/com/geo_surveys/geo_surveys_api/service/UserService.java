package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.entity.User;
import com.geo_surveys.geo_surveys_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Service for user.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    /**
     * Main authentication function.
     *
     * @param login    username.
     * @param password user password.
     * @return token.
     */
    public String generateToken(String login, String password) {
        return login + password;


    }


}
