package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.UserTaskEntityDto;
import com.geo_surveys.geo_surveys_api.entity.UserTask;
import com.geo_surveys.geo_surveys_api.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service for userTask.
 */
@Service
public class UserTaskService {

    /**
     * Database repository for userTask.
     */
    @Autowired
    private UserTaskRepository userTaskRepo;

    /**
     * Check user-task relation.
     *
     * @param userId user id.
     * @param taskId task id.
     * @return true if relation exist.
     */
    public boolean existRelation(Long userId, Long taskId){
        return userTaskRepo.findByUserIdAndTaskId(userId, taskId) != null;
    }

    /**
     * Convert UserTask entity to UserTaskDto
     *
     * @param userTask the Task entity
     * @return TaskDto
     */
    public UserTaskEntityDto convertToDto(UserTask userTask) {
        return new UserTaskEntityDto(
                userTask.getUserTaskId(),
                userTask.getUser().getUserId(),
                userTask.getTask().getTaskId(),
                userTask.getCreatedAt(),
                userTask.getUpdatedAt()
        );
    }

    /**
     * Convert list of UserTask entities to list of UserTaskEntityDto
     *
     * @param userTasks list of UserTask entities
     * @return list of UserTaskEntityDto
     */
    public List<UserTaskEntityDto> convertToDtoList(List<UserTask> userTasks) {
        return userTasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


