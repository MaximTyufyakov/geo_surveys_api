package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.PointEntityDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service for point.
 */
@Service
public class PointService {

    /**
     * Database repository for point.
     */
    @Autowired
    private PointRepository pointRepo;

    /**
     * Convert Point entity to PointDto
     *
     * @param point the Task entity
     * @return TaskDto
     */
    public PointEntityDto convertToDto(Point point) {
        return new PointEntityDto(
                point.getPointId(),
                point.getTask().getTaskId(),
                point.getNumber(),
                point.getDescription(),
                point.getCompleted(),
                point.getCreatedAt(),
                point.getUpdatedAt()
        );
    }

    /**
     * Convert list of Point entities to list of PointEntityDto
     *
     * @param points list of Point entities
     * @return list of PointEntityDto
     */
    public List<PointEntityDto> convertToDtoList(List<Point> points) {
        return points.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


