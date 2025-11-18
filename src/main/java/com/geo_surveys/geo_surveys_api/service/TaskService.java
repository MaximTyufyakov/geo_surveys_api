package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.TaskEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service for user.
 * Contains authentication functions.
 */
@Service
public class TaskService {

    /**
     * Database repository for task.
     */
    @Autowired
    private TaskRepository taskRepo;

    /**
     * Get all tasks for user.
     *
     * @param userId is user id
     * @return list of tasks.
     */
    public List<TaskEntityDto> allForUser(Long userId){
        return convertToDtoList(taskRepo.findAllByUserId(userId));
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


