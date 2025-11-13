package com.geo_surveys.geo_surveys_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * Entity for user.
 */
@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // Columns.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long userid;

    @Column(length = 50, nullable = false, unique = true)
    @Check(constraints = "length(login::text) >= 3")
    @Setter(AccessLevel.NONE)
    private String login;

    @Column(length = 100, nullable = false)
    @Setter(AccessLevel.NONE)
    private String password;

    @Column(nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    @CreationTimestamp
    private Instant created_at;

    @Column(nullable = false,
            columnDefinition = "timestamp with time zone default now()")
    @UpdateTimestamp
    private Instant updated_at;

}


