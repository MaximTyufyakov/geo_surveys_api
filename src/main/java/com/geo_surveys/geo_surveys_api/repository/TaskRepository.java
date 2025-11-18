package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for task.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find all tasks associated with a specific user through UserTask relationship.
     *
     * @param userId the ID of the user.
     * @return list of tasks associated with the user.
     */
    @Query("SELECT t FROM Task t JOIN t.userTasks ut WHERE ut.user.userId = :userId")
    List<Task> findAllByUserId(@Param("userId") Long userId);
}