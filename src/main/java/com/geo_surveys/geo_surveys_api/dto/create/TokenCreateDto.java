package com.geo_surveys.geo_surveys_api.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for user's token create (request).
 */
public record TokenCreateDto(

        @NotBlank(message = "Логин не может быть пустым")
        String login,

        @NotBlank(message = "Пароль не может быть пустым")
        String password
) {

}
