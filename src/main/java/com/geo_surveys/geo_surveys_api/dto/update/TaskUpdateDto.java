package com.geo_surveys.geo_surveys_api.dto.update;

import com.geo_surveys.geo_surveys_api.dto.create.VideoCreateDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for task update (request).
 */
public record TaskUpdateDto(

        @NotNull(message = "Отстутсвует поле task_id")
        @Min(value = 1, message = "task_id должно быть больше 0")
        Long task_id,

        @NotNull(message = "Отстутсвует поле updatedPoints")
        List<PointUpdateDto> updatedPoints,

        String report,

        @NotNull(message = "Отстутсвует поле createdVideos")
        List<VideoCreateDto> createdVideos,

        @NotNull(message = "Отстутсвует поле deletedVideos")
        List<Long> deletedVideos
) {

}
