package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for task.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}