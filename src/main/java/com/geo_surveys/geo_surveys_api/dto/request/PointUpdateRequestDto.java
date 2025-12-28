package com.geo_surveys.geo_surveys_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * DTO for point update.
 */
@Getter
public class PointUpdateRequestDto{
        @NotNull(message = "Отстутсвует поле pointId")
        @Min(value = 1, message = "pointId должно быть больше 0")
        Long pointId;

        @NotNull(message = "Отстутсвует поле completed")
        Boolean completed;

}
