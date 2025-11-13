package com.geo_surveys.geo_surveys_api.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for point update (request).
 */
public record PointUpdateDto(

        @NotNull(message = "Отстутсвует поле pointid.")
        @Min(value = 1, message = "pointid должно быть больше 0.")
        Long pointid,

        @NotNull(message = "Отстутсвует поле completed.")
        Boolean completed
) {

}
