package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Video;
import com.geo_surveys.geo_surveys_api.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service for video.
 */
@Service
public class VideoService {

    /**
     * Database repository for video.
     */
    @Autowired
    private VideoRepository videoRepo;

    /**
     * Convert Video entity to VideoDto
     *
     * @param video the Task entity
     * @return TaskDto
     */
    public VideoEntityDto convertToDto(Video video) {
        return new VideoEntityDto(
                video.getVideoId(),
                video.getTask().getTaskId(),
                video.getTitle(),
                video.getUrl(),
                null,
                video.getCreatedAt(),
                video.getUpdatedAt()
        );
    }

    /**
     * Convert list of Video entities to list of VideoEntityDto
     *
     * @param videos list of Video entities
     * @return list of VideoEntityDto
     */
    public List<VideoEntityDto> convertToDtoList(List<Video> videos) {
        return videos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


