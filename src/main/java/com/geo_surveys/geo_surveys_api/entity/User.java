package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.Instant;

/**
 * Entity for user.
 */
@Entity
@Table(name = "user", schema = "public")
@Getter
@NoArgsConstructor
public class User {

    // Columns.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login", length = 50, nullable = false, unique = true)
    @Check(constraints = "length(login::text) >= 3")
    private String login;

    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updatedAt;

}


