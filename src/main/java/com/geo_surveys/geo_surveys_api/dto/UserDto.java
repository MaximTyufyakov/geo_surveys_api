package com.geo_surveys.geo_surveys_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for user body.
 */
public record UserDto(

        @NotNull(message = "Отстутсвует поле login.")
        @Size(min = 3, message = "Поле login должно содержать от 3 символов.")
        String login,

        @NotNull(message = "Отстутсвует поле password.")
        String password
) {

}
