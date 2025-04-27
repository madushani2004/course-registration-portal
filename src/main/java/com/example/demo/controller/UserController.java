package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing user-related operations.
 * Provides endpoints for CRUD operations and searching users.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a new UserController with the given UserService.
     * @param userService The service to handle user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     * @param user The user data to create
     * @return ResponseEntity containing the created user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    /**
     * Retrieves all users.
     * @return ResponseEntity containing a list of all users
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve
     * @return ResponseEntity containing the requested user
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Updates an existing user.
     * @param id The ID of the user to update
     * @param updatedUser The updated user data
     * @return ResponseEntity containing the updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody  UserDto updatedUser) {
        return ResponseEntity.ok(userService.updateByUserId(id,updatedUser));
    }

    /**
     * Searches for users by name and/or role.
     * @param name The name to search for (optional)
     * @param role The role to filter by (optional)
     * @return ResponseEntity containing a list of matching users
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Role role) {
        return ResponseEntity.ok(userService.searchUsers(name, role));
    }

}
