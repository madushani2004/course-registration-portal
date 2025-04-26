package com.institute.portal.enrollmentservice.service;

import com.institute.portal.enrollmentservice.entity.Enrollment;
import com.institute.portal.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public Enrollment enroll(Long studentId, Long courseId) {
        if (repository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .studentId(studentId)
                .courseId(courseId)
                .enrollmentDate(LocalDate.now().toString())
                .build();

        return repository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsForStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    public void dropEnrollment(Long id) {
        repository.deleteById(id);
    }
}
