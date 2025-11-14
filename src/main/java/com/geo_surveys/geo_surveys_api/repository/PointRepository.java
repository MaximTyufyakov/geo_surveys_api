package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for point.
 */
@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}