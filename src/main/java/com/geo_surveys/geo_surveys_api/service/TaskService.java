package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.PointEntityDto;
import com.geo_surveys.geo_surveys_api.dto.entity.TaskEntityDto;
import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import com.geo_surveys.geo_surveys_api.dto.update.TaskUpdateDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.entity.Video;
import com.geo_surveys.geo_surveys_api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Service for task.
 */
@Service
public class TaskService {

    /**
     * Database repository for task.
     */
    @Autowired
    private TaskRepository taskRepo;

    /**
     * Point service.
     */
    @Autowired
    PointService pointService;

    /**
     * Video service.
     */
    @Autowired
    VideoService videoService;

    /**
     * UserTask service.
     */
    @Autowired
    UserTaskService userTaskService;

    /**
     * Get all tasks for user.
     *
     * @param userId is user id
     * @return list of tasks.
     */
    public List<TaskEntityDto> getAll(Long userId) {
        return convertToDtoList(taskRepo.findAllByUserId(userId));
    }

    /**
     * Get one task by id.
     *
     * @param userId is user id
     * @param taskId is required task id
     * @return map of task, points and videos.
     * @throws AccessDeniedException   if user doesn't have access to task.
     * @throws EntityNotFoundException if entity not found.
     */
    public Map<String, Object> getOne(Long userId, Long taskId)
            throws AccessDeniedException, EntityNotFoundException {
        // Check relation.
        if (userTaskService.existRelation(userId, taskId)) {
            Task task = taskRepo.findById(taskId).orElse(null);

            // Check task exist.
            if (task != null) {
                return convertToMap(task);
            } else {
                throw new EntityNotFoundException("Задание не найдено");
            }
        } else {
            throw new AccessDeniedException("Нет доступа");
        }
    }

    /**
     * Update task.
     *
     * @param userId        is user id
     * @param taskUpdateDto is new data
     * @return map of task, points and videos.
     * @throws AccessDeniedException   if user doesn't have access to task.
     * @throws EntityNotFoundException if task not found.
     */
    public Map<String, Object> update(Long userId, TaskUpdateDto taskUpdateDto)
            throws AccessDeniedException, EntityNotFoundException {
        // Check user-task relation.
        if (userTaskService.existRelation(userId, taskUpdateDto.task_id())) {
            Task task = taskRepo.findById(taskUpdateDto.task_id()).orElse(null);

            // Check task exist.
            if (task != null) {
                // Points update.
                List<Point> updatedPoints = pointService.updateList(
                        task.getPoints(),
                        taskUpdateDto.updatedPoints()
                );

                // Videos delete.
                List<Video> updatedVideos = videoService.deleteList(
                        task.getVideos(),
                        taskUpdateDto.deletedVideos()
                );

                // Videos create.
                updatedVideos = videoService.createList(
                        task,
                        updatedVideos,
                        taskUpdateDto.createdVideos()
                );

                // Completed check.
                boolean completed =
                        pointService.allCompleted(updatedPoints) && !updatedVideos.isEmpty();

                // Task update.
                task.setCompleted(completed);
                task.setReport(taskUpdateDto.report());
                return convertToMap(taskRepo.save(task));

            } else {
                throw new EntityNotFoundException("Задание не найдено");
            }
        } else {
            throw new AccessDeniedException("Нет доступа");
        }

    }

    /**
     * Parse task entity with points and videos to map with dto;
     *
     * @param task is target entity.
     * @return map with dto.
     */
    private Map<String, Object> convertToMap(Task task) {
        TaskEntityDto taskDto = convertToDto(task);
        List<PointEntityDto> pointsDto =
                pointService.convertToDtoList(task.getPoints());
        List<VideoEntityDto> videosDto =
                videoService.convertToDtoList(task.getVideos());

        return Map.ofEntries(
                Map.entry("task", taskDto),
                Map.entry("points", pointsDto),
                Map.entry("videos", videosDto)
        );
    }

    /**
     * Convert Task entity to TaskDto
     *
     * @param task the Task entity
     * @return TaskDto
     */
    public TaskEntityDto convertToDto(Task task) {
        return new TaskEntityDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getLatitude(),
                task.getLongitude(),
                task.getCompleted(),
                task.getReport(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    /**
     * Convert list of Task entities to list of TaskEntityDto
     *
     * @param tasks list of Task entities
     * @return list of TaskEntityDto
     */
    public List<TaskEntityDto> convertToDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


