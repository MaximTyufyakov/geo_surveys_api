package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.entity.TaskEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.service.TaskService;
import com.geo_surveys.geo_surveys_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for work with task.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * Get all tasks for user.
     *
     * @param authentication is authentication data.
     * @return list of tasks.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TaskEntityDto>> allForUser(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.allForUser(userId));
    }
}
