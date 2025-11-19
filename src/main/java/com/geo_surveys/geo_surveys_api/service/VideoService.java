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
     * @param deleted list with videos id.
     */
    public List<Video> deleteList(List<Video> videos, List<Long> deleted) {
        for (Long id : deleted) {
            videoRepo.deleteById(id);
            videos.removeIf(
                    video -> video.getVideoId().equals(id)
            );
        }
        return videos;
    }

    /**
     * Create videos.
     *
     * @param task             is parent task.
     * @param videos           is list with all videos in task.
     * @param createdVideoDtos is target data.
     * @return updated video list.
     */
    public List<Video> createList(
            Task task,
            List<Video> videos,
            List<VideoCreateDto> createdVideoDtos) {
        for (VideoCreateDto createdVideoDto : createdVideoDtos) {
            // Generate unique title.
            List<String> titles = videos.stream()
                    .map(Video::getTitle)
                    .toList();
            String title = generateUniqueName(titles, createdVideoDto.title());

            Video video = new Video();
            video.setTask(task);
            video.setTitle(title);
            video.setUrl(createdVideoDto.file() + " " + Instant.now());

            videos.add(videoRepo.save(video));
        }
        return videos;
    }

    /**
     *
     * @param names
     * @param name
     * @return
     */
    private String generateUniqueName(List<String> names, String name) {
        // Создаем множество для быстрого поиска существующих имен
        java.util.Set<String> existingNames = new java.util.HashSet<>(names);
        String result = name;
        int counter = 1;

        // Проверяем существование имени и генерируем новое при необходимости
        while (existingNames.contains(result)) {
            result = name + " (" + counter + ")";
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


