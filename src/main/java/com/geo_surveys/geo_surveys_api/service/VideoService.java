package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.create.VideoCreateDto;
import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.entity.Video;
import com.geo_surveys.geo_surveys_api.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
     * Delete videos.
     *
     * @param videos  list with target videos
     * @param deleted list with videos id.
     */
    public void deleteList(List<Video> videos, List<Long> deleted) {
        for (Long id : deleted) {
            videoRepo.deleteById(id);
            videos.removeIf(
                    video -> video.getVideoId().equals(id)
            );
        }
    }

    /**
     * Create videos.
     *
     * @param task             is parent task.
     * @param videos           is list with all videos in task.
     * @param createdVideoDtos is target data.
     */
    public void createList(
            Task task,
            List<Video> videos,
            List<VideoCreateDto> createdVideoDtos) {
        // All titles.
        List<String> titles = videos.stream()
                .map(Video::getTitle)
                .collect(Collectors.toList());
        for (VideoCreateDto createdVideoDto : createdVideoDtos) {
            // Generate unique title.
            String title = generateUniqueTitle(titles, createdVideoDto.title());
            titles.add(title);

            // Create video.
            Video video = new Video();
            video.setTask(task);
            video.setTitle(title);
            video.setUrl(createdVideoDto.file() + " " + Instant.now());

            // Add video.
            videos.add(videoRepo.save(video));
        }
    }

    /**
     *
     * @param titles all videos titles
     * @param title new title
     * @return unique title
     */
    private String generateUniqueTitle(List<String> titles, String title) {
        // Create HashSet for titles.
        java.util.Set<String> existingTitles = new java.util.HashSet<>(titles);
        String result = title;
        int counter = 1;

        // Check title exist.
        while (existingTitles.contains(result)) {
            // Change if title exist.
            result = title + " (" + counter + ")";
            counter++;
        }
        return result;
    }

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


