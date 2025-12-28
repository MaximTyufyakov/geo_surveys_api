package com.geo_surveys.geo_surveys_api.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

/**
 * DTO for point entity response.
 */
@AllArgsConstructor
@Getter
public class PointResponseDto {
        Long pointId;

        Integer number;

        String description;

        Boolean completed;

}
