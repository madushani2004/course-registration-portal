package com.example.demo.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for enrollment information.
 * Contains basic enrollment details including student and course IDs,
 * enrollment timestamp, and approval status.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDto {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Timestamp enrolledAt;
    private Boolean isApproved;
}