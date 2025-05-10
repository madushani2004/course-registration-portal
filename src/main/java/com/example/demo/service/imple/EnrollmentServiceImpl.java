package com.example.demo.service.imple;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.EnrollmentDetailDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the EnrollmentService interface.
 * Provides business logic for enrollment-related operations including:
 * - Enrolling students in courses
 * - Managing enrollment records
 * - Retrieving enrollment details
 * - Filtering enrollments by various criteria
 */
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor for EnrollmentServiceImpl.
     * @param enrollmentRepository Repository for enrollment data access
     * @param userRepository Repository for user data access
     * @param courseRepository Repository for course data access
     * @param modelMapper Mapper for converting between DTOs and entities
     */
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Enrolls a student in a course.
     * Validates student and course existence, checks for duplicate enrollments.
     * @param enrollmentDto The enrollment data to create
     * @return The created enrollment as a DTO
     * @throws RuntimeException if student/course not found or duplicate enrollment exists
     */
    @Override
    public EnrollmentDto enrollStudent(EnrollmentDto enrollmentDto) {
        if (!userRepository.existsById(enrollmentDto.getStudentId())) {
            throw new RuntimeException("Student not found with ID: " + enrollmentDto.getStudentId());
        }
        
        if (!courseRepository.existsById(enrollmentDto.getCourseId())) {
            throw new RuntimeException("Course not found with ID: " + enrollmentDto.getCourseId());
        }
        boolean exists = enrollmentRepository.findByStudentIdAndCourseId(
                enrollmentDto.getStudentId(),
                enrollmentDto.getCourseId()
        ).isPresent();

        if (exists) {
            throw new RuntimeException("Student is already enrolled in this course.");
        }

        enrollmentDto.setEnrolledAt(Timestamp.valueOf(LocalDateTime.now()));

        Enrollment enrollment = modelMapper.map(enrollmentDto, Enrollment.class);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return modelMapper.map(saved, EnrollmentDto.class);
    }

    /**
     * Retrieves all enrollments from the database.
     * @return List of all enrollments as DTOs
     */
    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(e -> modelMapper.map(e, EnrollmentDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific enrollment by its ID.
     * @param id The ID of the enrollment to retrieve
     * @return The enrollment as a DTO
     * @throws RuntimeException if the enrollment is not found
     */
    @Override
    public EnrollmentDto getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
        return modelMapper.map(enrollment, EnrollmentDto.class);
    }

    /**
     * Deletes an enrollment by its ID.
     * @param id The ID of the enrollment to delete
     */
    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    /**
     * Updates an existing enrollment with new data.
     * Only non-null fields from the DTO will be updated.
     * @param id The ID of the enrollment to update
     * @param enrollmentDto The new enrollment data
     * @return The updated enrollment as a DTO
     * @throws RuntimeException if the enrollment is not found
     */
    @Override
    public EnrollmentDto updateEnrollment(Long id, EnrollmentDto enrollmentDto) {
        Enrollment existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));

        if (enrollmentDto.getStudentId() != null) {
            existing.setStudentId(enrollmentDto.getStudentId());
        }
        if (enrollmentDto.getCourseId() != null) {
            existing.setCourseId(enrollmentDto.getCourseId());
        }
        if (enrollmentDto.getIsApproved() != null) {
            existing.setIsApproved(enrollmentDto.getIsApproved());
        }

        Enrollment updated = enrollmentRepository.save(existing);
        return modelMapper.map(updated, EnrollmentDto.class);
    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student
     * @return List of enrollments for the student as DTOs
     */
    @Override
    public List<EnrollmentDto> getCoursesByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all students enrolled in a specific course.
     * @param courseId The ID of the course
     * @return List of enrollments for the course as DTOs
     */
    @Override
    public List<EnrollmentDto> getStudentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves all enrollments with detailed information including student and course details.
     * @return List of detailed enrollment DTOs
     */
    @Override
    public List<EnrollmentDetailDto> getAllEnrollmentsWithDetails() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentDetailDto> detailedEnrollments = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            EnrollmentDetailDto detailDto = createEnrollmentDetailDto(enrollment);
            detailedEnrollments.add(detailDto);
        }
        
        return detailedEnrollments;
    }
    
    /**
     * Retrieves a specific enrollment with detailed information by its ID.
     * @param id The ID of the enrollment to retrieve
     * @return The detailed enrollment DTO
     * @throws RuntimeException if the enrollment is not found
     */
    @Override
    public EnrollmentDetailDto getEnrollmentWithDetailsById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
        
        return createEnrollmentDetailDto(enrollment);
    }
    
    /**
     * Retrieves enrollments within a specific date range.
     * @param startDate The start date in ISO format (yyyy-MM-dd)
     * @param endDate The end date in ISO format (yyyy-MM-dd)
     * @return List of detailed enrollment DTOs within the date range
     * @throws RuntimeException if there's an error processing the date range
     * @throws IllegalArgumentException if the end date is before the start date
     */
    @Override
    public List<EnrollmentDetailDto> getEnrollmentsByDateRange(String startDate, String endDate) {
        try {
            // Parse string dates to LocalDateTime with validation
            LocalDate parsedStartDate = LocalDate.parse(startDate);
            LocalDate parsedEndDate = LocalDate.parse(endDate);
            
            // Validate date range
            if (parsedEndDate.isBefore(parsedStartDate)) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
            
            // Set time components properly for inclusive range
            LocalDateTime start = parsedStartDate.atStartOfDay();
            LocalDateTime end = parsedEndDate.atTime(23, 59, 59, 999999999); // End of day
            
            // Convert LocalDateTime to Timestamp for repository query
            Timestamp startTimestamp = Timestamp.valueOf(start);
            Timestamp endTimestamp = Timestamp.valueOf(end);
            
            // Execute query with BETWEEN operator
            List<Enrollment> enrollments = enrollmentRepository.findByEnrolledAtBetween(startTimestamp, endTimestamp);
            
            // Map to DTOs
            return enrollments.stream()
                    .map(this::createEnrollmentDetailDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error filtering enrollments by date range: " + e.getMessage(), e);
        }
    }
    
    /**
     * Creates a detailed enrollment DTO with student and course information.
     * @param enrollment The enrollment entity to convert
     * @return The detailed enrollment DTO
     * @throws RuntimeException if student or course is not found
     */
    private EnrollmentDetailDto createEnrollmentDetailDto(Enrollment enrollment) {
        // Map basic enrollment data
        EnrollmentDetailDto detailDto = modelMapper.map(enrollment, EnrollmentDetailDto.class);
        
        // Fetch and map student details
        User student = userRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + enrollment.getStudentId()));
        detailDto.setStudent(modelMapper.map(student, UserDto.class));
        
        // Fetch and map course details
        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + enrollment.getCourseId()));
        detailDto.setCourse(modelMapper.map(course, CourseDto.class));
        
        return detailDto;
    }
}
