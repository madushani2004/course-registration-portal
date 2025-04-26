package com.institute.portal.enrollmentservice.controller;

import com.institute.portal.enrollmentservice.entity.Enrollment;
import com.institute.portal.enrollmentservice.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public Enrollment enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return service.enroll(studentId, courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollments(@PathVariable Long studentId) {
        return service.getEnrollmentsForStudent(studentId);
    }

    @DeleteMapping("/{id}")
    public void dropEnrollment(@PathVariable Long id) {
        service.dropEnrollment(id);
    }
}
