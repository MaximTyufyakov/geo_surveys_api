package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.entity.PointEntityDto;
import com.geo_surveys.geo_surveys_api.dto.update.PointUpdateDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
     * Update point list by data in dto.
     *
     * @param points          is target points.
     * @param pointUpdateDtos is new data.
     */
    public void updateList(List<Point> points, List<PointUpdateDto> pointUpdateDtos) {
        // Map for get point by id.
        Map<Long, Point> pointMap = points.stream()
                .collect(Collectors.toMap(Point::getPointId, Function.identity()));
        // Update.
        for (PointUpdateDto pointUpdateDto : pointUpdateDtos) {
            Point point = pointMap.get(pointUpdateDto.point_id());
            // Point exist.
            if (point != null) {
                update(point, pointUpdateDto);
            }
        }
    }

    /**
     * Update point by data in dto.
     *
     * @param point          is target point.
     * @param pointUpdateDto is new data.
     */
    private void update(Point point, PointUpdateDto pointUpdateDto) {
        // Point update.
        point.setCompleted(pointUpdateDto.completed());
        pointRepo.save(point);
    }

    /**
     * Check points completed.
     *
     * @param points is target points.
     * @return true if all points completed.
     */
    public boolean allCompleted(List<Point> points){
        for (Point point : points) {
            if (point.getCompleted() == false){
                return false;
            }
        }
        return true;
    }

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


