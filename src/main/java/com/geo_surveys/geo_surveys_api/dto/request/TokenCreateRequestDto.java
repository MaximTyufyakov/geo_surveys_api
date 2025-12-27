package com.geo_surveys.geo_surveys_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * DTO for user's token create.
 */
@Getter
public class TokenCreateRequestDto {
    @NotBlank(message = "Логин не может быть пустым")
    String login;

    @NotBlank(message = "Пароль не может быть пустым")
    String password;

}
