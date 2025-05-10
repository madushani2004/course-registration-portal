package com.example.demo.repository;

import com.example.demo.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Enrollment entity.
 * Provides methods for enrollment-related database operations including:
 * - Counting enrollments by approval status
 * - Finding enrollments by student or course
 * - Finding enrollments within a date range
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    /**
     * Counts the number of enrollments with the specified approval status.
     * @param isApproved The approval status to count
     * @return The count of enrollments with the given status
     */
    long countByIsApproved(boolean isApproved);
    
    /**
     * Finds all enrollments for a specific student.
     * @param studentId The ID of the student
     * @return List of enrollments for the student
     */
    List<Enrollment> findByStudentId(Long studentId);

    /**
     * Finds all enrollments for a specific course.
     * @param courseId The ID of the course
     * @return List of enrollments for the course
     */
    List<Enrollment> findByCourseId(Long courseId);

    /**
     * Finds a specific enrollment by student and course IDs.
     * @param studentId The ID of the student
     * @param courseId The ID of the course
     * @return Optional containing the enrollment if found
     */
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
    
    /**
     * Finds enrollments with enrollment date between the given range (inclusive).
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     * @return List of enrollments within the date range
     */
    @Query("SELECT e FROM Enrollment e WHERE e.enrolledAt BETWEEN :startDate AND :endDate")
    List<Enrollment> findByEnrolledAtBetween(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
