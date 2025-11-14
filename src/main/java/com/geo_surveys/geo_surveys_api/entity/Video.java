package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Entity for video.
 */
@Entity
@Table( name = "video",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"task_id", "title"}))
@Getter
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @Setter
    private Task task;

    @Column(name = "title", length = 100, nullable = false)
    @Setter
    private String title;

    @Column(name = "url", nullable = false, unique = true)
    @Setter
    private String url;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;
}
