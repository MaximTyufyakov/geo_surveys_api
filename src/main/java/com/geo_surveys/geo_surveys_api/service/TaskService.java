package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.response.TaskResponseDto;
import com.geo_surveys.geo_surveys_api.dto.request.TaskUpdateRequestDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.entity.Video;
import com.geo_surveys.geo_surveys_api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<TaskResponseDto> getAll(Long userId) {
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
    public TaskResponseDto getOne(Long userId, Long taskId)
            throws AccessDeniedException, EntityNotFoundException {
        // Check relation.
        if (userTaskService.existRelation(userId, taskId)) {
            Task task = taskRepo.findById(taskId).orElse(null);

            // Check task exist.
            if (task != null) {
                return convertToDto(task);
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
     * @param taskUpdateRequestDto is new data
     * @return map of task, points and videos.
     * @throws AccessDeniedException   if user doesn't have access to task.
     * @throws EntityNotFoundException if task not found.
     */
    public TaskResponseDto update(Long userId, TaskUpdateRequestDto taskUpdateRequestDto)
            throws AccessDeniedException, EntityNotFoundException {
        // Check user-task relation.
        if (userTaskService.existRelation(userId, taskUpdateRequestDto.getTaskId())) {
            Task task = taskRepo.findById(taskUpdateRequestDto.getTaskId()).orElse(null);

            // Check task exist.
            if (task != null) {
                // Points update.
                List<Point> points = task.getPoints();
                pointService.updateList(
                        points,
                        taskUpdateRequestDto.getUpdatedPoints()
                );

                // Videos delete.
                List<Video> videos = task.getVideos();
                videoService.deleteList(
                        videos,
                        taskUpdateRequestDto.getDeletedVideosId()
                );

                // Videos create.
                videoService.createList(
                        task,
                        videos,
                        taskUpdateRequestDto.getCreatedVideos()
                );

                // Completed check.
                boolean completed =
                        pointService.allCompleted(points) && !videos.isEmpty();

                // Task update.
                task.setCompleted(completed);
                task.setReport(taskUpdateRequestDto.getReport());
                return convertToDto(taskRepo.save(task));

            } else {
                throw new EntityNotFoundException("Задание не найдено");
            }
        } else {
            throw new AccessDeniedException("Нет доступа");
        }

    }

    /**
     * Convert Task entity to TaskDto
     *
     * @param task the Task entity
     * @return TaskDto
     */
    public TaskResponseDto convertToDto(Task task) {
        return new TaskResponseDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getLatitude(),
                task.getLongitude(),
                task.getCompleted(),
                task.getReport(),
                pointService.convertToDtoList(task.getPoints()),
                videoService.convertToDtoList(task.getVideos())
        );
    }

    /**
     * Convert list of Task entities to list of TaskEntityDto
     *
     * @param tasks list of Task entities
     * @return list of TaskEntityDto
     */
    public List<TaskResponseDto> convertToDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


