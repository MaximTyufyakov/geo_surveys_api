package com.geo_surveys.geo_surveys_api.dto.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * DTO for user synchronization (response and request).
 */
public record UserEntityDto(
        @NotNull(message = "Отсутствует поле userid")
        @Min(value = 1, message = "userid должно быть больше 0.")
        Long userid,

        @NotBlank(message = "Логин не может быть пустым")
        @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов")
        String login,

        @NotBlank(message = "Пароль не может быть пустым.")
        String password,

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at,

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at) {
}
