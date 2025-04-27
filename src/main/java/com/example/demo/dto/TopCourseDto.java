package com.example.demo.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for top courses information.
 * Contains course details including course ID, title, and the number of enrollments
 * for statistical and reporting purposes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopCourseDto {
    private Long courseId;
    private String title;
    private Long enrollmentCount;
}