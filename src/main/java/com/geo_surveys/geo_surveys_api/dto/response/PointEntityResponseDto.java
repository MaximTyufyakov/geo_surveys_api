package com.geo_surveys.geo_surveys_api.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

/**
 * DTO for point entity response and synchronization with local database.
 */
@AllArgsConstructor
public class PointEntityResponseDto {
        @NotNull(message = "Отсутствует поле point_id")
        @Min(value = 1, message = "point_id должно быть больше 0")
        Long point_id;

        @NotNull(message = "Отсутствует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id;

        @NotNull(message = "Отсутствует поле number")
        @Min(value = 1, message = "number должно быть больше 0")
        Integer number;

        @NotBlank(message = "Описание не может быть пустым")
        String description;

        @NotNull(message = "Отсутствует поле completed")
        Boolean completed;

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at;

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at;

}
