package com.institute.portal.course.service.repo;

import com.institute.portal.course.service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCategory(String category);

    List<Course> findByLevel(String level);
}
