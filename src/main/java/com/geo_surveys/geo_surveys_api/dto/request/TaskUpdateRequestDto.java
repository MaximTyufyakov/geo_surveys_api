package com.geo_surveys.geo_surveys_api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

/**
 * DTO for task update.
 */
@Getter
public class TaskUpdateRequestDto{
        @NotNull(message = "Отстутсвует поле taskId")
        @Min(value = 1, message = "taskId должно быть больше 0")
        Long taskId;

        @Valid
        @NotNull(message = "Отстутсвует поле updatedPoints")
        List<PointUpdateRequestDto> updatedPoints;

        String report;

        @Valid
        @NotNull(message = "Отстутсвует поле createdVideos")
        List<VideoCreateRequestDto> createdVideos;

        @NotNull(message = "Отстутсвует поле deletedVideosId")
        List<Long> deletedVideosId;

}
