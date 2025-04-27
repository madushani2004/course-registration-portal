package com.example.demo.service;

import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.EnrollmentDetailDto;

import java.util.List;

/**
 * Service interface for managing enrollment-related operations.
 * Provides methods for CRUD operations and retrieving enrollment information.
 */
public interface EnrollmentService {
    /**
     * Enrolls a student in a course.
     * @param enrollmentDto The enrollment data containing student and course information
     * @return The created enrollment as a DTO
     */
    EnrollmentDto enrollStudent(EnrollmentDto enrollmentDto);

    /**
     * Retrieves all enrollments from the database.
     * @return List of all enrollments as DTOs
     */
    List<EnrollmentDto> getAllEnrollments();

    /**
     * Retrieves a specific enrollment by its ID.
     * @param id The ID of the enrollment to retrieve
     * @return The enrollment as a DTO
     */
    EnrollmentDto getEnrollmentById(Long id);

    /**
     * Deletes an enrollment by its ID.
     * @param id The ID of the enrollment to delete
     */
    void deleteEnrollment(Long id);

    /**
     * Updates an existing enrollment with new data.
     * @param id The ID of the enrollment to update
     * @param enrollmentDto The new enrollment data
     * @return The updated enrollment as a DTO
     */
    EnrollmentDto updateEnrollment(Long id, EnrollmentDto enrollmentDto);

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student
     * @return List of enrollments for the specified student
     */
    List<EnrollmentDto> getCoursesByStudentId(Long studentId);

    /**
     * Retrieves all students enrolled in a specific course.
     * @param courseId The ID of the course
     * @return List of enrollments for the specified course
     */
    List<EnrollmentDto> getStudentsByCourseId(Long courseId);
    
    /**
     * Retrieves all enrollments with detailed information including student and course details.
     * @return List of detailed enrollment DTOs
     */
    List<EnrollmentDetailDto> getAllEnrollmentsWithDetails();

    /**
     * Retrieves a specific enrollment with detailed information by its ID.
     * @param id The ID of the enrollment to retrieve
     * @return The detailed enrollment DTO
     */
    EnrollmentDetailDto getEnrollmentWithDetailsById(Long id);

    /**
     * Retrieves enrollments within a specific date range.
     * @param startDate The start date of the range (inclusive)
     * @param endDate The end date of the range (inclusive)
     * @return List of detailed enrollment DTOs within the date range
     */
    List<EnrollmentDetailDto> getEnrollmentsByDateRange(String startDate, String endDate);
}
