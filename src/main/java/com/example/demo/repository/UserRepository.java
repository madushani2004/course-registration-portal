package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for User entity.
 * Provides methods for user-related database operations including:
 * - Counting users by role and enabled status
 * - Finding users by name (case-insensitive)
 * - Finding users by role
 * - Finding users by role and name combination
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Counts the number of users with the specified role and enabled status.
     * @param role The role to count
     * @param isEnabled The enabled status to count
     * @return The count of users with the given role and status
     */
    long countByRoleAndIsEnabled(Role role, boolean isEnabled);

    /**
     * Finds users whose full names contain the given string (case-insensitive).
     * @param name The name to search for
     * @return List of matching users
     */
    List<User> findByFullNameContainingIgnoreCase(String name);

    /**
     * Finds all users with the specified role.
     * @param role The role to search for
     * @return List of users with the given role
     */
    List<User> findByRole(Role role);

    /**
     * Finds users with the specified role whose full names contain the given string (case-insensitive).
     * @param role The role to search for
     * @param name The name to search for
     * @return List of matching users
     */
    List<User> findByRoleAndFullNameContainingIgnoreCase(Role role, String name);
}
