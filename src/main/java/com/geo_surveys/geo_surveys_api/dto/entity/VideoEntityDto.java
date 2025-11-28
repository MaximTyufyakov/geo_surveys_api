package com.geo_surveys.geo_surveys_api.dto.entity;

import jakarta.validation.constraints.*;

import java.time.Instant;

/**
 * DTO for video entity (response for synchronization and request).
 */
public record VideoEntityDto(
        @NotNull(message = "Отсутствует поле video_id")
        @Min(value = 1, message = "video_id должно быть больше 0")
        Long video_id,

        @NotNull(message = "Отсутствует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotBlank(message = "Название видео не может быть пустым")
        @Size(max = 100, message = "Название не должно превышать 100 символов")
        String title,

        String url,
        String file,

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at,

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at
) {
    @AssertTrue(message = "Хотя бы одно из полей url или file должно быть заполнено")
    public boolean isUrlOrFileNotNull() {
        return url != null || file != null;
    }
}


