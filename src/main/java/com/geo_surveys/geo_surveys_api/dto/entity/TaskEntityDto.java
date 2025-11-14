package com.geo_surveys.geo_surveys_api.dto.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record TaskEntityDto(
        @NotNull(message = "Отсутствует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotBlank(message = "Название задачи не может быть пустым")
        @Size(max = 100, message = "Название не должно превышать 100 символов")
        String title,

        String description,

        String coordinates,

        @NotNull(message = "Отсутствует поле completed")
        Boolean completed,

        String report,

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at,

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at
) {
}