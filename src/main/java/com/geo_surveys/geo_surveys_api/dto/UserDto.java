package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull
        String login,

        @NotNull
        String password
) {
}
