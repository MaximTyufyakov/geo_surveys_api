package com.geo_surveys.geo_surveys_api.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO for video create (request).
 */
public record VideoCreateDto(

        @NotBlank(message = "Название не может быть пустым")
        String title,

        @NotBlank(message = "Файл не может быть пустым")
        String file, // Base64 encoded video

        @NotBlank(message = "Формат не может быть пустым")
        String format,

        @NotNull(message = "Широта не может быть пустой")
        BigDecimal latitude,

        @NotNull(message = "Долгота не может быть пустой")
        BigDecimal longitude
) {

}
