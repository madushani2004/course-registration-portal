package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import java.util.List;

/**
 * Service interface for managing user-related operations.
 * Provides methods for CRUD operations and searching users.
 */
public interface UserService {

    /**
     * Creates a new user from the provided user DTO.
     * @param user The user data to create
     * @return The created user as a DTO
     */
    UserDto createUser(UserDto user);

    /**
     * Retrieves all users from the database.
     * @return List of all users as DTOs
     */
    List<UserDto> getAll();

    /**
     * Retrieves a specific user by their ID.
     * @param id The ID of the user to retrieve
     * @return The user as a DTO
     */
    UserDto getUserById(Long id);

    /**
     * Updates an existing user with new data.
     * @param id The ID of the user to update
     * @param user The new user data
     * @return The updated user as a DTO
     */
    UserDto updateByUserId(Long id, UserDto user);
    
    /**
     * Searches for users by their full name.
     * @param name The name to search for
     * @return List of matching users as DTOs
     */
    List<UserDto> searchUsersByName(String name);
    
    /**
     * Searches for users by both name and role.
     * @param name The name to search for
     * @param role The role to filter by
     * @return List of matching users as DTOs
     */
    List<UserDto> searchUsers(String name, Role role);
}
