package com.interswitch.users.controller;

import com.interswitch.users.model.User;
import com.interswitch.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        User createdUser = userService.createUser(user).get();
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get User by Username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws InterruptedException, ExecutionException {
        User user = userService.getUserByUsername(username).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get All Users with Pagination
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws InterruptedException, ExecutionException {
        List<User> users = userService.getAllUsers(page, size).get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws InterruptedException, ExecutionException {
        userService.deleteUser(id).get();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
