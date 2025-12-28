package com.geo_surveys.geo_surveys_api.dto.sync;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.Instant;

/**
 * DTO for user_task synchronization with local database.
 */
@AllArgsConstructor
public class UserTaskEntityResponseDto {
        @NotNull(message = "Отсутствует поле userTaskId")
        @Min(value = 1, message = "userTaskId должно быть больше 0")
        Long userTaskId;

        @NotNull(message = "Отсутствует поле userId")
        @Min(value = 1, message = "userId должно быть больше 0")
        Long userId;

        @NotNull(message = "Отсутствует поле taskId")
        @Min(value = 1, message = "taskId должно быть больше 0")
        Long taskId;

        @NotNull(message = "Отсутствует поле createdAt")
        Instant createdAt;

        @NotNull(message = "Отсутствует поле updatedAt")
        Instant updatedAt;

}
