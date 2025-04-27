package com.example.demo.controller;

import com.example.demo.dto.CourseDto;
import com.example.demo.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing course-related operations.
 * Provides endpoints for CRUD operations and searching courses.
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    /**
     * Constructs a new CourseController with the given CourseService.
     * @param courseService The service to handle course operations
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Creates a new course.
     * @param courseDto The course data to create
     * @return ResponseEntity containing the created course
     */
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    /**
     * Retrieves all courses.
     * @return ResponseEntity containing a list of all courses
     */
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    /**
     * Retrieves a course by its ID.
     * @param id The ID of the course to retrieve
     * @return ResponseEntity containing the requested course
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    /**
     * Updates an existing course.
     * @param id The ID of the course to update
     * @param courseDto The updated course data
     * @return ResponseEntity containing the updated course
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }

    /**
     * Deletes a course by its ID.
     * @param id The ID of the course to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Searches for courses by title.
     * @param title The title to search for (optional)
     * @return ResponseEntity containing a list of matching courses
     */
    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCoursesByTitle(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(courseService.searchCoursesByTitle(title));
    }
}
