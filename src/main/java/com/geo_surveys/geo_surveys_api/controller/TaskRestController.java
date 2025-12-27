package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.response.TaskEntityResponseDto;
import com.geo_surveys.geo_surveys_api.dto.request.TaskUpdateRequestDto;
import com.geo_surveys.geo_surveys_api.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for work with task.
 */
@RestController
@RequestMapping("/tasks")
public class TaskRestController {

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
    public ResponseEntity<Map<String, List<TaskEntityResponseDto>>> getAll(Authentication authentication) {
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
                    .body(Map.ofEntries(
                            Map.entry("task", taskService.getOne(userId, taskId))
                    ));
        } catch (AccessDeniedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
    }

    /**
     * Update task with points and videos.
     *
     * @param authentication is authentication data.
     * @param taskUpdateRequestDto is updated data.
     * @return map: actual task, points, videos.
     */
    @PutMapping("/save")
    public ResponseEntity<Map<String, Object>> update(
            Authentication authentication,
            @Valid @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        Long userId = (Long) authentication.getPrincipal();
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.ofEntries(
                            Map.entry("task", taskService.update(userId, taskUpdateRequestDto))
                    ));
        } catch (AccessDeniedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.ofEntries(
                            Map.entry("message", e.getMessage())
                    ));
        }
    }
}
