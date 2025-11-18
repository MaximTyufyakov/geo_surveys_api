package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.entity.TaskEntityDto;
import com.geo_surveys.geo_surveys_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controller for work with task.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /**
     * Task service.
     */
    @Autowired
    TaskService taskService;

    /**
     * Get all tasks for user.
     *
     * @param authentication is authentication data.
     * @return list of tasks.
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, List<TaskEntityDto>>> getAll(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.ofEntries(
                        Map.entry("tasks", taskService.getAll(userId))
                ));

    }

    /**
     * Get one task with points and videos.
     *
     * @param authentication is authentication data.
     * @param taskId is required task id.
     * @return map: task, points, videos.
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Map<String, Object>> getOne(
            Authentication authentication, @PathVariable Long taskId) {
        Long userId = (Long) authentication.getPrincipal();
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(taskService.getOne(userId, taskId));
        } catch (AccessDeniedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
        catch (NullPointerException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
    }
}
