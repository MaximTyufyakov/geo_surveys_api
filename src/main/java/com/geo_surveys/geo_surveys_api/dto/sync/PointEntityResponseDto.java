package com.geo_surveys.geo_surveys_api.dto.sync;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.Instant;

/**
 * DTO for point entity response and synchronization with local database.
 */
@AllArgsConstructor
public class PointEntityResponseDto {
        @NotNull(message = "Отсутствует поле pointId")
        @Min(value = 1, message = "pointId должно быть больше 0")
        Long pointId;

        @NotNull(message = "Отсутствует поле taskId")
        @Min(value = 1, message = "taskId должно быть больше 0")
        Long taskId;

        @NotNull(message = "Отсутствует поле number")
        @Min(value = 1, message = "number должно быть больше 0")
        Integer number;

        @NotBlank(message = "Описание не может быть пустым")
        String description;

        @NotNull(message = "Отсутствует поле completed")
        Boolean completed;

        @NotNull(message = "Отсутствует поле createdAt")
        Instant createdAt;

        @NotNull(message = "Отсутствует поле updatedAt")
        Instant updatedAt;

}
