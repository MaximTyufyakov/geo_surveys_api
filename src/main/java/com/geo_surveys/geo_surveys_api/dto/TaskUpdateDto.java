package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for task body.
 */
public record TaskUpdateDto(

        @NotNull(message = "Отстутсвует поле taskid.")
        @Min(value = 1, message = "taskid должно быть больше 0.")
        Long taskid,

        @NotNull(message = "Отстутсвует поле completed.")
        Boolean completed,

        String report
) {

}
