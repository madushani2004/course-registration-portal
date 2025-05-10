package com.example.demo.controller;

import com.example.demo.dto.EnrollmentDetailDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing enrollment-related operations.
 * Provides endpoints for CRUD operations, filtering enrollments by date range,
 * and retrieving enrollments by student or course.
 */
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * Constructs a new EnrollmentController with the given EnrollmentService.
     * @param enrollmentService The service to handle enrollment operations
     */
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Enrolls a student in a course.
     * @param dto The enrollment data containing student and course information
     * @return ResponseEntity containing the created enrollment
     */
    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody EnrollmentDto dto) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(dto));
    }

    /**
     * Retrieves all enrollments with detailed information.
     * Optionally filters enrollments by date range if startDate and endDate parameters are provided.
     * 
     * @param startDate Optional start date for filtering (format: yyyy-MM-dd)
     * @param endDate Optional end date for filtering (format: yyyy-MM-dd)
     * @return ResponseEntity containing a list of enrollments with details
     */
    @GetMapping
    public ResponseEntity<?> getAllWithDetails(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        try {
            // If both date parameters are provided, filter by date range
            if (startDate != null && endDate != null) {
                List<EnrollmentDetailDto> filteredEnrollments = enrollmentService.getEnrollmentsByDateRange(startDate, endDate);
                return ResponseEntity.ok(filteredEnrollments);
            }
            
            // Otherwise, return all enrollments
            return ResponseEntity.ok(enrollmentService.getAllEnrollmentsWithDetails());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format or range: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing request: " + e.getMessage());
        }
    }

    /**
     * Retrieves a specific enrollment with detailed information by its ID.
     * @param id The ID of the enrollment to retrieve
     * @return ResponseEntity containing the enrollment details
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDetailDto> getByIdWithDetails(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentWithDetailsById(id));
    }

    /**
     * Deletes an enrollment by its ID.
     * @param id The ID of the enrollment to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates an existing enrollment.
     * @param id The ID of the enrollment to update
     * @param dto The updated enrollment data
     * @return ResponseEntity containing the updated enrollment
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDto> updateEnrollment(
            @PathVariable Long id,
            @RequestBody EnrollmentDto dto
    ) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, dto));
    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student
     * @return ResponseEntity containing a list of enrollments for the student
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDto>> getCoursesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getCoursesByStudentId(studentId));
    }

    /**
     * Retrieves all students enrolled in a specific course.
     * @param courseId The ID of the course
     * @return ResponseEntity containing a list of enrollments for the course
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDto>> getStudentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getStudentsByCourseId(courseId));
    }
}