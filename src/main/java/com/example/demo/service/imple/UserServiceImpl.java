package com.example.demo.service.imple;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface.
 * Provides business logic for user-related operations including:
 * - Creating new users
 * - Retrieving users
 * - Updating existing users
 * - Searching users by name and role
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor for UserServiceImpl.
     * @param userRepository Repository for user data access
     * @param modelMapper Mapper for converting between DTOs and entities
     */
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new user from the provided user DTO.
     * Sets creation timestamp and handles duplicate username/email cases.
     * @param userDto The user data to create
     * @return The created user as a DTO
     * @throws RuntimeException if username or email already exists
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setFullName(userDto.getFullName());
            user.setRole(userDto.getRole());
            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Username or Email already exists.");
        }
    }

    /**
     * Retrieves all users from the database.
     * @return List of all users as DTOs
     */
    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific user by their ID.
     * @param id The ID of the user to retrieve
     * @return The user as a DTO
     * @throws RuntimeException if the user is not found
     */
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Updates an existing user with new data.
     * Only non-null fields from the DTO will be updated.
     * @param id The ID of the user to update
     * @param userDto The new user data
     * @return The updated user as a DTO
     * @throws RuntimeException if the user is not found
     */
    @Override
    public UserDto updateByUserId(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getFullName() != null) {
            existingUser.setFullName(userDto.getFullName());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }
        if (userDto.getRole() != null) {
            existingUser.setRole(userDto.getRole());
        }
        if (userDto.getIsEnabled() != null) {
            existingUser.setIsEnabled(userDto.getIsEnabled());
        }
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    /**
     * Searches for users by their full name.
     * If the name is null or empty, returns all users.
     * @param name The name to search for
     * @return List of matching users as DTOs
     */
    @Override
    public List<UserDto> searchUsersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAll();
        }
        return userRepository.findByFullNameContainingIgnoreCase(name).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    
    /**
     * Searches for users by both name and role.
     * Handles various combinations of null parameters:
     * - If role is null, searches by name only
     * - If name is null/empty, searches by role only
     * - If both are provided, searches by both criteria
     * @param name The name to search for
     * @param role The role to filter by
     * @return List of matching users as DTOs
     */
    @Override
    public List<UserDto> searchUsers(String name, Role role) {
        if (role == null) {
            // If only role is null, search by name only
            return searchUsersByName(name);
        } else if (name == null || name.trim().isEmpty()) {
            // If only name is null or empty, search by role only
            return userRepository.findByRole(role).stream()
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .collect(Collectors.toList());
        } else {
            // If both parameters are provided, search by both
            return userRepository.findByRoleAndFullNameContainingIgnoreCase(role, name).stream()
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .collect(Collectors.toList());
        }
    }
}
