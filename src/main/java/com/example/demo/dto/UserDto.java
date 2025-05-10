package com.example.demo.dto;


import com.example.demo.model.Role;
import lombok.*;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for user-related operations.
 * Contains all necessary user information including authentication details,
 * personal information, role, and account status.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private Role role;
    private Timestamp createdAt;
    private Boolean isEnabled;
}
