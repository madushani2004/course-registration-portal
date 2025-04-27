package com.example.demo.repository;

import com.example.demo.dto.TopCourseDto;
import com.example.demo.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Course entity.
 * Provides methods for course-related database operations including:
 * - Searching courses by title
 * - Counting enabled courses
 * - Finding top enrolled courses
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Finds courses whose titles contain the given string (case-insensitive).
     * @param title The title to search for
     * @return List of matching courses
     */
    List<Course> findByTitleContainingIgnoreCase(String title);

    /**
     * Counts the number of courses with the specified enabled status.
     * @param isEnabled The enabled status to count
     * @return The count of courses with the given status
     */
    long countByIsEnabled(boolean isEnabled);

    /**
     * Finds the top enrolled courses.
     * Returns a list of courses ordered by their enrollment count in descending order.
     * Only includes enabled courses.
     * @param pageable Pagination information
     * @return List of top courses with their enrollment counts
     */
    @Query("SELECT new com.example.demo.dto.TopCourseDto(c.id, c.title, COUNT(e.id)) " +
            "FROM Course c LEFT JOIN Enrollment e ON c.id = e.courseId " +
            "WHERE c.isEnabled = true " +
            "GROUP BY c.id, c.title " +
            "ORDER BY COUNT(e.id) DESC")
    List<TopCourseDto> findTopCourses(Pageable pageable);
}
