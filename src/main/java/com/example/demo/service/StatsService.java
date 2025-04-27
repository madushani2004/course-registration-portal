package com.example.demo.service;

import com.example.demo.dto.StatsDto;
import com.example.demo.dto.TopCourseDto;

import java.util.List;

/**
 * Service interface for retrieving system statistics and analytics.
 * Provides methods for accessing various system metrics and performance data.
 */
public interface StatsService {
    /**
     * Retrieves comprehensive system statistics including:
     * - Count of enabled students, admins, and instructors
     * - Count of enabled courses
     * - Count of approved and pending enrollments
     * @return StatsDto containing all system statistics
     */
    StatsDto getSystemStats();

    /**
     * Retrieves the top 10 courses based on enrollment numbers.
     * @return List of TopCourseDto containing course details and enrollment counts
     */
    List<TopCourseDto> getTop10Courses();
}
