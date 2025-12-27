package com.geo_surveys.geo_surveys_api.dto.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for video entity response and synchronization with local database.
 */
@AllArgsConstructor
public class VideoEntityResponseDto {
        @NotNull(message = "Отсутствует поле video_id")
        @Min(value = 1, message = "video_id должно быть больше 0")
        Long video_id;

        @NotNull(message = "Отсутствует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id;

        @NotBlank(message = "Название видео не может быть пустым")
        @Size(max = 100, message = "Название не должно превышать 100 символов")
        String title;

        String url;
        String file;

        @NotNull(message = "Широта не может быть пустой")
        BigDecimal latitude;

        @NotNull(message = "Долгота не может быть пустой")
        BigDecimal longitude;

        @NotNull(message = "Отсутствует поле created_at")
        Instant created_at;

        @NotNull(message = "Отсутствует поле updated_at")
        Instant updated_at;

    @AssertTrue(message = "Хотя бы одно из полей url или file должно быть заполнено")
    public boolean isUrlOrFileNotNull() {
        return url != null || file != null;
    }
}


