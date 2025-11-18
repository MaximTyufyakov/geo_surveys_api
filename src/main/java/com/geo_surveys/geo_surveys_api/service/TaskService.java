package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.PointEntityDto;
import com.geo_surveys.geo_surveys_api.dto.entity.TaskEntityDto;
import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.repository.TaskRepository;
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
     * @throws AccessDeniedException if user doesn't have access to task.
     * @throws NullPointerException  if entity not found.
     */
    public Map<String, Object> getOne(Long userId, Long taskId)
            throws AccessDeniedException, NullPointerException {
        // Check relation.
        if (userTaskService.existRelation(userId, taskId)) {
            Task task = taskRepo.findById(taskId).orElse(null);

            // Check task exist.
            if (task != null) {
                // Parsing.
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
            } else {
                throw new NullPointerException("Задание не найдено");
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
    public TaskEntityDto convertToDto(Task task) {
        return new TaskEntityDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getCoordinates(),
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


