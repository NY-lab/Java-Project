package com.interswitch.users.service;

import com.interswitch.users.model.User;
import com.interswitch.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Async Create User
    @Async("customAsyncExecutor")
    public CompletableFuture<User> createUser(User user) {
        logger.info("[ASYNC] Creating user: {}", user.getUsername());
        User savedUser = userRepository.save(user);
        logger.info("[ASYNC] User created successfully: {}", savedUser.getUsername());
        return CompletableFuture.completedFuture(savedUser);
    }

    // Async Get User by Username
    @Async("customAsyncExecutor")
    public CompletableFuture<User> getUserByUsername(String username) {
        logger.info("[ASYNC] Fetching user with username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            logger.warn("[ASYNC] User not found: {}", username);
            throw new RuntimeException("User not found");
        }
        return CompletableFuture.completedFuture(user.get());
    }

    // Async Get All Users with Pagination
    @Async("customAsyncExecutor")
    public CompletableFuture<List<User>> getAllUsers(int page, int size) {
        logger.info("[ASYNC] Fetching all users - Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        logger.info("[ASYNC] Found {} users", users.size());
        return CompletableFuture.completedFuture(users);
    }

    // Async Delete User
    @Async("customAsyncExecutor")
    public CompletableFuture<Void> deleteUser(Long id) {
        logger.info("[ASYNC] Deleting user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.warn("[ASYNC] User with ID {} not found", id);
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        logger.info("[ASYNC] User with ID {} deleted successfully", id);
        return CompletableFuture.completedFuture(null);
    }
}
