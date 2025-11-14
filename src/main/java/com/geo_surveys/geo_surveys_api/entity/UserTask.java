package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Entity for user_task (many to many).
 */
@Entity
@Table( name = "user_task",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userid", "taskid"}))
@Getter
@NoArgsConstructor
public class UserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usertaskid")
    private Long userTaskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskid")
    private Task task;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;
}
