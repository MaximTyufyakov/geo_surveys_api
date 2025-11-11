package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for video body.
 */
public record VideoDto(

        @NotNull(message = "Отстутсвует поле taskid.")
        @Min(value = 1, message = "taskid должно быть больше 0.")
        Long taskid,

        @NotNull(message = "Отстутсвует поле title.")
        String title,

        @NotNull(message = "Отстутсвует поле file.")
        String file
) {

}
