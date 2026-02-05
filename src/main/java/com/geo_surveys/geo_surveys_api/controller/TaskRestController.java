package com.geo_surveys.geo_surveys_api.controller;

import com.geo_surveys.geo_surveys_api.dto.response.MessageResponseDto;
import com.geo_surveys.geo_surveys_api.dto.response.PointResponseDto;
import com.geo_surveys.geo_surveys_api.dto.response.TaskResponseDto;
import com.geo_surveys.geo_surveys_api.dto.request.TaskUpdateRequestDto;
import com.geo_surveys.geo_surveys_api.dto.response.VideoResponseDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.entity.Video;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<List<TaskResponseDto>> getAll(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasksToDtoList(taskService.getAll(userId)));

    }

    /**
     * Get one task with points and videos.
     *
     * @param authentication is authentication data.
     * @param taskId is required task id.
     * @return map: task, points, videos.
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getOne(
            Authentication authentication, @PathVariable Long taskId) {
        Long userId = (Long) authentication.getPrincipal();
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(taskToDto(taskService.getOne(userId, taskId)));
        } catch (AccessDeniedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponseDto(e.getMessage()));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponseDto(e.getMessage()));
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
    public ResponseEntity<Object> update(
            Authentication authentication,
            @Valid @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        Long userId = (Long) authentication.getPrincipal();
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(taskToDto(taskService.update(userId, taskUpdateRequestDto)));
        } catch (AccessDeniedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponseDto(e.getMessage()));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponseDto(e.getMessage()));
        }
    }

    /**
     * Convert Task entity to TaskDto
     *
     * @param task the Task entity
     * @return TaskDto
     */
    public TaskResponseDto taskToDto(Task task) {
        return new TaskResponseDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getLatitude(),
                task.getLongitude(),
                task.getCompleted(),
                task.getReport(),
                pointsToDtoList(task.getPoints()),
                videosToDtoList(task.getVideos())
        );
    }

    /**
     * Convert list of Task entities to list of TaskEntityDto
     *
     * @param tasks list of Task entities
     * @return list of TaskEntityDto
     */
    public List<TaskResponseDto> tasksToDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::taskToDto)
                .collect(Collectors.toList());

    }

    /**
     * Convert Point entity to PointDto
     *
     * @param point the Task entity
     * @return TaskDto
     */
    public PointResponseDto pointToDto(Point point) {
        return new PointResponseDto(
                point.getPointId(),
                point.getNumber(),
                point.getDescription(),
                point.getCompleted()
        );
    }

    /**
     * Convert list of Point entities to list of PointEntityDto
     *
     * @param points list of Point entities
     * @return list of PointEntityDto
     */
    public List<PointResponseDto> pointsToDtoList(List<Point> points) {
        return points.stream()
                .map(this::pointToDto)
                .collect(Collectors.toList());

    }

    /**
     * Convert Video entity to VideoDto
     *
     * @param video the Task entity
     * @return TaskDto
     */
    public VideoResponseDto videoToDto(Video video) {
        return new VideoResponseDto(
                video.getVideoId(),
                video.getTitle(),
                video.getLatitude(),
                video.getLongitude()
        );
    }

    /**
     * Convert list of Video entities to list of VideoEntityDto
     *
     * @param videos list of Video entities
     * @return list of VideoEntityDto
     */
    public List<VideoResponseDto> videosToDtoList(List<Video> videos) {
        return videos.stream()
                .map(this::videoToDto)
                .collect(Collectors.toList());

    }
}
