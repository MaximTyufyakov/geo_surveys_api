package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.create.VideoCreateDto;
import com.geo_surveys.geo_surveys_api.dto.entity.VideoEntityDto;
import com.geo_surveys.geo_surveys_api.dto.update.PointUpdateDto;
import com.geo_surveys.geo_surveys_api.entity.Task;
import com.geo_surveys.geo_surveys_api.entity.Video;
import com.geo_surveys.geo_surveys_api.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
     * Service for work with S3.
     */
    @Autowired
    private S3Service s3Service;

    /**
     * Delete videos.
     *
     * @param videos  list with target videos.
     * @param deleted list with videos id.
     */
    public void deleteList(List<Video> videos, List<Long> deleted) {
        // Map for get video by id.
        Map<Long, Video> videosMap = videos.stream()
                .collect(Collectors.toMap(Video::getVideoId, Function.identity()));
        for (Long id : deleted) {
            Video video = videosMap.get(id);
            if (video != null) {
                // Delete from S3.
                s3Service.deleteFromS3(video.getUrl());
                // Delete from db.
                videoRepo.deleteById(id);
                // Delete from list.
                videos.removeIf(
                        elem -> elem.getVideoId().equals(id)
                );
            }
        }
    }

    /**
     * Create videos.
     *
     * @param task             is parent task.
     * @param videos           is list with all videos in task.
     * @param createdVideoDtos is target data.
     * @throws IllegalArgumentException – if upload video in S3 fails.
     */
    public void createList(
            Task task,
            List<Video> videos,
            List<VideoCreateDto> createdVideoDtos) throws IllegalArgumentException {
        // All titles.
        List<String> titles = videos.stream()
                .map(Video::getTitle)
                .collect(Collectors.toList());
        for (VideoCreateDto createdVideoDto : createdVideoDtos) {
            // Generate unique title.
            String title = generateUniqueTitle(titles,
                    createdVideoDto.title().trim());
            titles.add(title);

            // Create video.
            Video video = new Video();
            video.setTask(task);
            video.setTitle(title);

            // Save in S3.
            String key = String.format(
                    "%s/%s.%s",
                    task.getTitle(),
                    title,
                    createdVideoDto.format()
            );
            String url = s3Service.uploadVideo(createdVideoDto.file(), key, createdVideoDto.format());
            video.setUrl(url);

            // Save in db and add in list.
            videos.add(videoRepo.save(video));
        }
    }

    /**
     *
     * @param titles all videos titles
     * @param title  new title
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


