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
    private Long userid;

    @Column(length = 50, nullable = false, unique = true)
    @Check(constraints = "length(login::text) >= 3")
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant created_at;

    @Column(nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    private Instant updated_at;

}


