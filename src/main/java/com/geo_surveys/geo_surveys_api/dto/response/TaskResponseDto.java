package com.geo_surveys.geo_surveys_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for task entity response.
 */
@AllArgsConstructor
@Getter
public class TaskResponseDto {
    Long taskId;

    String title;

    String description;

    BigDecimal latitude;

    BigDecimal longitude;

    Boolean completed;

    String report;

    List<PointResponseDto> points;

    List<VideoResponseDto> videos;
}