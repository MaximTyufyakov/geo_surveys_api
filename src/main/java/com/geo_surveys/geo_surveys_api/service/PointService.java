package com.geo_surveys.geo_surveys_api.service;

import com.geo_surveys.geo_surveys_api.dto.response.PointEntityResponseDto;
import com.geo_surveys.geo_surveys_api.dto.request.PointUpdateRequestDto;
import com.geo_surveys.geo_surveys_api.entity.Point;
import com.geo_surveys.geo_surveys_api.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param pointUpdateRequestDtos is new data.
     */
    public void updateList(List<Point> points, List<PointUpdateRequestDto> pointUpdateRequestDtos) {
        // Map for get point by id.
        Map<Long, Point> pointMap = points.stream()
                .collect(Collectors.toMap(Point::getPointId, Function.identity()));
        // Update.
        for (PointUpdateRequestDto pointUpdateRequestDto : pointUpdateRequestDtos) {
            Point point = pointMap.get(pointUpdateRequestDto.getPoint_id());
            // Point exist.
            if (point != null) {
                update(point, pointUpdateRequestDto);
            }
        }
    }

    /**
     * Update point by data in dto.
     *
     * @param point          is target point.
     * @param pointUpdateRequestDto is new data.
     */
    private void update(Point point, PointUpdateRequestDto pointUpdateRequestDto) {
        // Point update.
        point.setCompleted(pointUpdateRequestDto.getCompleted());
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
    public PointEntityResponseDto convertToDto(Point point) {
        return new PointEntityResponseDto(
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
    public List<PointEntityResponseDto> convertToDtoList(List<Point> points) {
        return points.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

}


