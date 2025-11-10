package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TaskDto(
        @NotNull
        @Min(1)
        Integer taskid,

        @NotNull
        Boolean completed,

        String report
) {
}
