package com.geo_surveys.geo_surveys_api.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * DTO for task entity response and synchronization with local database.
 */
@AllArgsConstructor
public class TaskEntityResponseDto {
    @NotNull(message = "Отсутствует поле task_id")
    @Min(value = 1, message = "task_id должно быть больше 0")
    Long task_id;

    @NotBlank(message = "Название задачи не может быть пустым")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    String title;

    String description;

    BigDecimal latitude;

    BigDecimal longitude;

    @NotNull(message = "Отсутствует поле completed")
    Boolean completed;

    String report;

    @NotNull(message = "Отсутствует поле created_at")
    Instant created_at;

    @NotNull(message = "Отсутствует поле updated_at")
    Instant updated_at;

    @Valid
    @NotNull(message = "Отсутствует поле points")
    List<PointEntityResponseDto> points;

    @Valid
    @NotNull(message = "Отсутствует поле videos")
    List<VideoEntityResponseDto> videos;
}