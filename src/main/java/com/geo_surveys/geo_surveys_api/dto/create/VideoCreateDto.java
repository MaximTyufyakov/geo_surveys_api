package com.geo_surveys.geo_surveys_api.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for video create (request).
 */
public record VideoCreateDto(

        @NotNull(message = "Отстутсвует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotBlank(message = "Название не может быть пустым")
        String title,

        @NotBlank(message = "Файл не может быть пустым")
        String file
) {

}
