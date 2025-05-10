package com.example.demo.dto;


import lombok.*;

/**
 * Data Transfer Object (DTO) for system statistics.
 * Contains counts of various enabled entities in the system including:
 * - Number of enabled students, admins, and instructors
 * - Number of enabled courses
 * - Number of approved and not approved enrollments
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsDto {
    private long enabledStudents;
    private long enabledAdmin;
    private long enabledInstructor;
    private long enabledCourses;
    private long approvedEnrollments;
    private long notApprovedEnrollments;
}