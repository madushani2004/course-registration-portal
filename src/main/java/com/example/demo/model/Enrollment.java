package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * Entity class representing a student's enrollment in a course.
 * Contains all necessary fields for enrollment management including:
 * - Student and course identification
 * - Enrollment timestamp
 * - Approval status
 * 
 * The unique constraint ensures a student can only enroll in a course once.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @CreationTimestamp
    @Column(name = "enrolled_at")
    private Timestamp enrolledAt;

    @Column(name = "is_approved")
    private Boolean isApproved;
}