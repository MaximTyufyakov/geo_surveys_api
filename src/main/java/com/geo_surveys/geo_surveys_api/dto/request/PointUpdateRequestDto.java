package com.geo_surveys.geo_surveys_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * DTO for point update.
 */
@Getter
public class PointUpdateRequestDto{
        @NotNull(message = "Отстутсвует поле point_id")
        @Min(value = 1, message = "point_id должно быть больше 0")
        Long point_id;

        @NotNull(message = "Отстутсвует поле completed")
        Boolean completed;
        
}
