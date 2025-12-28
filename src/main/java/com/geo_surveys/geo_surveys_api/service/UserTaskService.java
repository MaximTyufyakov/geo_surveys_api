package com.geo_surveys.geo_surveys_api.service;

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

}


