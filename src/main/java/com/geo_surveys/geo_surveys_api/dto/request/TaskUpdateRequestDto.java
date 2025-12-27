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
        @NotNull(message = "Отстутсвует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id;

        @Valid
        @NotNull(message = "Отстутсвует поле updatedPoints")
        List<PointUpdateRequestDto> updatedPoints;

        String report;

        @Valid
        @NotNull(message = "Отстутсвует поле createdVideos")
        List<VideoCreateRequestDto> createdVideos;

        @NotNull(message = "Отстутсвует поле deletedVideos")
        List<Long> deletedVideos;

}
