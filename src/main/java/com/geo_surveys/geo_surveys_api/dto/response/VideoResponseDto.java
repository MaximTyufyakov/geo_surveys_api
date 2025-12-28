package com.geo_surveys.geo_surveys_api.dto.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for video entity response.
 */
@AllArgsConstructor
public class VideoResponseDto {
        Long videoId;

        String title;

        BigDecimal latitude;

        BigDecimal longitude;
}


