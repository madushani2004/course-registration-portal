package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * Entity class representing a user in the system.
 * Contains all necessary fields for user management including:
 * - Authentication details (username, email, password)
 * - Personal information (full name)
 * - Role and permissions
 * - Account status and creation timestamp
 */
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "is_enabled")
    private Boolean isEnabled;
}
