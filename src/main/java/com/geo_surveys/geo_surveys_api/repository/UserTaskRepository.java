package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for user_task.
 */
@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    /**
     * Find UserTask by user id and task id.
     *
     * @param userId the ID of the user.
     * @param taskId the ID of the task.
     * @return one UserTask.
     */
    @Query("SELECT ut FROM UserTask ut WHERE ut.user.userId = :userId AND ut.task.taskId = :taskId")
    UserTask findByUserIdAndTaskId(@Param("userId") Long userId, @Param("taskId") Long taskId);
}