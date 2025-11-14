package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for video.
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}