package com.example.demo.service.imple;

import com.example.demo.dto.StatsDto;
import com.example.demo.dto.TopCourseDto;
import com.example.demo.model.Role;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StatsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the StatsService interface.
 * Provides business logic for retrieving system statistics including:
 * - User counts by role and enabled status
 * - Course counts by enabled status
 * - Enrollment counts by approval status
 * - Top performing courses
 */
@Service
public class StatsServiceImpl implements StatsService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    /**
     * Constructor for StatsServiceImpl.
     * @param userRepository Repository for user data access
     * @param courseRepository Repository for course data access
     * @param enrollmentRepository Repository for enrollment data access
     */
    public StatsServiceImpl(UserRepository userRepository,
                            CourseRepository courseRepository,
                            EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Retrieves comprehensive system statistics including:
     * - Count of enabled students, admins, and instructors
     * - Count of enabled courses
     * - Count of approved and pending enrollments
     * @return StatsDto containing all system statistics
     */
    @Override
    public StatsDto getSystemStats() {
        long enabledStudents = userRepository.countByRoleAndIsEnabled(Role.STUDENT, true);
        long enabledAdmin = userRepository.countByRoleAndIsEnabled(Role.ADMIN, true);
        long enabledInstructor = userRepository.countByRoleAndIsEnabled(Role.INSTRUCTOR, true);
        long enabledCourses = courseRepository.countByIsEnabled(true);
        long approvedEnrollments = enrollmentRepository.countByIsApproved(true);
        long notApprovedEnrollments = enrollmentRepository.countByIsApproved(false);

        return StatsDto.builder()
                .enabledStudents(enabledStudents)
                .enabledAdmin(enabledAdmin)
                .enabledInstructor(enabledInstructor)
                .enabledCourses(enabledCourses)
                .approvedEnrollments(approvedEnrollments)
                .notApprovedEnrollments(notApprovedEnrollments)
                .build();
    }

    /**
     * Retrieves the top 10 courses based on enrollment numbers.
     * @return List of TopCourseDto containing course details and enrollment counts
     */
    @Override
    public List<TopCourseDto> getTop10Courses() {
        return courseRepository.findTopCourses(PageRequest.of(0, 10));
    }
}