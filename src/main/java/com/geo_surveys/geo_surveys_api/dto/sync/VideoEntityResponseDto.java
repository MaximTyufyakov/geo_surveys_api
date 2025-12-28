package com.geo_surveys.geo_surveys_api.dto.sync;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for video entity response and synchronization with local database.
 */
@AllArgsConstructor
public class VideoEntityResponseDto {
        @NotNull(message = "Отсутствует поле videoId")
        @Min(value = 1, message = "videoId должно быть больше 0")
        Long videoId;

        @NotNull(message = "Отсутствует поле taskId")
        @Min(value = 1, message = "taskId должно быть больше 0")
        Long taskId;

        @NotBlank(message = "Название видео не может быть пустым")
        @Size(max = 100, message = "Название не должно превышать 100 символов")
        String title;

        String url;
        String file;

        @NotNull(message = "Широта не может быть пустой")
        BigDecimal latitude;

        @NotNull(message = "Долгота не может быть пустой")
        BigDecimal longitude;

        @NotNull(message = "Отсутствует поле createdAt")
        Instant createdAt;

        @NotNull(message = "Отсутствует поле updatedAt")
        Instant updatedAt;

    @AssertTrue(message = "Хотя бы одно из полей url или file должно быть заполнено")
    public boolean isUrlOrFileNotNull() {
        return url != null || file != null;
    }
}


