package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
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
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "title", length = 100, nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "completed", nullable = false)
    @Setter
    private Boolean completed;

    @Column(name = "report")
    @Setter
    private String report;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("number ASC")
    private List<Point> points;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @OrderBy("title ASC")
    @Setter
    private List<Video> videos;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserTask> userTasks;
}
