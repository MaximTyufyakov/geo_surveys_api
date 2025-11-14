package com.geo_surveys.geo_surveys_api.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for task update (request).
 */
public record TaskUpdateDto(

        @NotNull(message = "Отстутсвует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotNull(message = "Отстутсвует поле completed")
        Boolean completed,

        String report
) {

}
