package com.geo_surveys.geo_surveys_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
