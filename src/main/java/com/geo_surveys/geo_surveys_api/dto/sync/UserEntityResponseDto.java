package com.geo_surveys.geo_surveys_api.dto.sync;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.time.Instant;

/**
 * DTO for user synchronization with local database.
 */
@AllArgsConstructor
public class UserEntityResponseDto {
        @NotNull(message = "Отсутствует поле userId")
        @Min(value = 1, message = "userId должно быть больше 0")
        Long userId;

        @NotBlank(message = "Логин не может быть пустым")
        @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов")
        String login;

        @NotBlank(message = "Пароль не может быть пустым")
        String password;

        @NotNull(message = "Отсутствует поле createdAt")
        Instant createdAt;

        @NotNull(message = "Отсутствует поле updatedAt")
        Instant updatedAt;
}
