package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for user_task.
 */
@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
}