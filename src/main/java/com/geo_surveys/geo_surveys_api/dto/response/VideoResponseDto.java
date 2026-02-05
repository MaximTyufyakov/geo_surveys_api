package com.geo_surveys.geo_surveys_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO for video entity response.
 */
@AllArgsConstructor
@Getter
public class VideoResponseDto {
        Long videoId;

        String title;

        BigDecimal latitude;

        BigDecimal longitude;
}


