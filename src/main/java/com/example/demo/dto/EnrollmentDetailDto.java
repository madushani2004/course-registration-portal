package com.example.demo.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for detailed enrollment information.
 * Contains enrollment details including student and course information,
 * enrollment status, and timestamps.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDetailDto {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Timestamp enrolledAt;
    private Boolean isApproved;
    
    // The associated student and course
    private UserDto student;
    private CourseDto course;
} 