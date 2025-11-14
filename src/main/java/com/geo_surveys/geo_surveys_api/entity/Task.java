package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for task.
 */
@Entity
@Table(name = "task", schema = "public")
@Getter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Long taskId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    // For PostgreSQL point type - using custom converter or separate class
    @Column(name = "coordinates", columnDefinition = "point")
    private String coordinates;

    @Column(name = "completed", nullable = false)
    @Setter
    private Boolean completed;

    @Column(name = "report")
    @Setter
    private String report;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Point> points;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @Setter
    private List<Video> videos;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserTask> userTasks;
}
