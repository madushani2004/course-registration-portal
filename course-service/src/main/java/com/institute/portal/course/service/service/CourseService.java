package com.institute.portal.course.service.service;

import com.institute.portal.course.service.entity.Course;
import com.institute.portal.course.service.repo.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Create a course
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // Update a course
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepo.findById(id);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(courseDetails.getTitle());
            course.setDescription(courseDetails.getDescription());
            course.setCategory(courseDetails.getCategory());
            course.setDuration(courseDetails.getDuration());
            course.setSeatsAvailable(courseDetails.getSeatsAvailable());
            course.setLevel(courseDetails.getLevel());
            course.setThumbnailUrl(courseDetails.getThumbnailUrl());
            course.setInstructor(courseDetails.getInstructor());

            return courseRepo.save(course);
        } else {
            return null;
        }
    }

    // Delete a course
    public boolean deleteCourse(Long id) {
        if (courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Get courses by category
    public List<Course> getCoursesByCategory(String category) {
        return courseRepo.findByCategory(category);
    }

    // Get courses by level
    public List<Course> getCoursesByLevel(String level) {
        return courseRepo.findByLevel(level);
    }
}
