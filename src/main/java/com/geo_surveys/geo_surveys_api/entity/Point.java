package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.Instant;

/**
 * Entity for point.
 */
@Entity
@Table( name = "point",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"task_id", "number"})
)
@Getter
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long pointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "number", nullable = false)
    @Check(constraints = "number > 0")
    private Integer number;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completed", nullable = false)
    @Setter
    private Boolean completed;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;
}


