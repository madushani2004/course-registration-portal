package com.example.demo.service.imple;

import com.example.demo.dto.CourseDto;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CourseService interface.
 * Provides business logic for course-related operations including:
 * - Creating new courses
 * - Retrieving courses
 * - Updating existing courses
 * - Deleting courses
 * - Searching courses by title
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor for CourseServiceImpl.
     * @param courseRepository Repository for course data access
     * @param modelMapper Mapper for converting between DTOs and entities
     */
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new course from the provided course DTO.
     * @param courseDto The course data to create
     * @return The created course as a DTO
     */
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDto.class);
    }

    /**
     * Retrieves all courses from the database.
     * @return List of all courses as DTOs
     */
    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific course by its ID.
     * @param id The ID of the course to retrieve
     * @return The course as a DTO
     * @throws RuntimeException if the course is not found
     */
    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        return modelMapper.map(course, CourseDto.class);
    }

    /**
     * Updates an existing course with new data.
     * Only non-null fields from the DTO will be updated.
     * @param id The ID of the course to update
     * @param courseDto The new course data
     * @return The updated course as a DTO
     * @throws RuntimeException if the course is not found
     */
    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        // Update fields only if they are provided in the DTO
        if (courseDto.getTitle() != null) {
            existing.setTitle(courseDto.getTitle());
        }
        if (courseDto.getDescription() != null) {
            existing.setDescription(courseDto.getDescription());
        }
        if (courseDto.getCategory() != null) {
            existing.setCategory(courseDto.getCategory());
        }
        if (courseDto.getInstructorId() != null) {
            existing.setInstructorId(courseDto.getInstructorId());
        }
        if (courseDto.getMaxCapacity() != null) {
            existing.setMaxCapacity(courseDto.getMaxCapacity());
        }
        if (courseDto.getStartDate() != null) {
            existing.setStartDate(courseDto.getStartDate());
        }
        if (courseDto.getEndDate() != null) {
            existing.setEndDate(courseDto.getEndDate());
        }
        if (courseDto.getIsEnabled() != null) {
            existing.setIsEnabled(courseDto.getIsEnabled());
        }

        Course updated = courseRepository.save(existing);
        return modelMapper.map(updated, CourseDto.class);
    }

    /**
     * Deletes a course by its ID.
     * @param id The ID of the course to delete
     */
    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    /**
     * Searches for courses by title.
     * If the title is null or empty, returns all courses.
     * @param title The title to search for
     * @return List of matching courses as DTOs
     */
    @Override
    public List<CourseDto> searchCoursesByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return getAllCourses();
        }
        return courseRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }
}
