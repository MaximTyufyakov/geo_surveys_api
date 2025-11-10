package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VideoDto(
        @NotNull
        @Min(1)
        Integer taskid,

        @NotNull
        String title,

        @NotNull
        String file
) {
}
