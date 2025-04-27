package com.example.demo.service;

import com.example.demo.dto.CourseDto;

import java.util.List;

/**
 * Service interface for managing course-related operations.
 * Provides methods for CRUD operations and searching courses.
 */
public interface CourseService {
    /**
     * Creates a new course from the provided course DTO.
     * @param courseDto The course data to create
     * @return The created course as a DTO
     */
    CourseDto createCourse(CourseDto courseDto);

    /**
     * Retrieves all courses from the database.
     * @return List of all courses as DTOs
     */
    List<CourseDto> getAllCourses();

    /**
     * Retrieves a specific course by its ID.
     * @param id The ID of the course to retrieve
     * @return The course as a DTO
     */
    CourseDto getCourseById(Long id);

    /**
     * Updates an existing course with new data.
     * @param id The ID of the course to update
     * @param courseDto The new course data
     * @return The updated course as a DTO
     */
    CourseDto updateCourse(Long id, CourseDto courseDto);

    /**
     * Deletes a course by its ID.
     * @param id The ID of the course to delete
     */
    void deleteCourse(Long id);

    /**
     * Searches for courses by their title.
     * @param title The title to search for
     * @return List of matching courses as DTOs
     */
    List<CourseDto> searchCoursesByTitle(String title);
}
