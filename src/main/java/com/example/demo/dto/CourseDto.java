package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for course-related operations.
 * Used to transfer course data between different layers of the application.
 * Contains all necessary fields for course management including basic information,
 * scheduling, and status.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Long instructorId;
    private Integer maxCapacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isEnabled;
}