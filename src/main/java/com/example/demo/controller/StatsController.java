package com.example.demo.controller;

import com.example.demo.dto.StatsDto;
import com.example.demo.dto.TopCourseDto;
import com.example.demo.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing statistics-related operations.
 * Provides endpoints for retrieving system-wide statistics and top courses.
 */
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    /**
     * Constructs a new StatsController with the given StatsService.
     * @param statsService The service to handle statistics operations
     */
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * Retrieves system-wide statistics including total courses, students, and enrollments.
     * @return ResponseEntity containing the system statistics
     */
    @GetMapping
    public ResponseEntity<StatsDto> getStats() {
        return ResponseEntity.ok(statsService.getSystemStats());
    }

    /**
     * Retrieves the top 10 most popular courses based on enrollment count.
     * @return ResponseEntity containing a list of top courses with their enrollment counts
     */
    @GetMapping("/top-courses")
    public ResponseEntity<List<TopCourseDto>> getTopCourses() {
        return ResponseEntity.ok(statsService.getTop10Courses());
    }
}
