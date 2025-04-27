package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class representing a course in the system.
 * Contains all necessary fields for course management including:
 * - Basic information (title, description, category)
 * - Instructor assignment
 * - Capacity and scheduling details
 * - Course status
 */
@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String description;

    @Column(length = 50)
    private String category;

    @Column(name = "instructor_id", nullable = false)
    private Long instructorId; // Assume mapped from user-service

    @Column(name = "max_capacity")
    private Integer maxCapacity = 30;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "is_enabled")
    private Boolean isEnabled;
}
