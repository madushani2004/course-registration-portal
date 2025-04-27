package com.institute.portal.course.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private int duration; // In hours
    private int seatsAvailable;
    private String level;
    private String thumbnailUrl;
    private String instructor;
}
