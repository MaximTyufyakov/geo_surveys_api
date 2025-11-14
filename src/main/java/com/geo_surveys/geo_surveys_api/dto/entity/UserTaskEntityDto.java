package com.geo_surveys.geo_surveys_api.dto.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

/**
 * DTO for user_task entity (response and request).
 */
public record UserTaskEntityDto(
        @NotNull(message = "Отсутствует поле user_task_id")
        @Min(value = 1, message = "user_task_id должно быть больше 0")
        Long user_task_id,

        @NotNull(message = "Отсутствует поле user_id")
        @Min(value = 1, message = "user_id должно быть больше 0")
        Long user_id,

        @NotNull(message = "Отсутствует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at,

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at
) {
}
