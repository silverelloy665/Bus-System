package com.busbooking.service;

import com.busbooking.dao.UserRepository;
import com.busbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for User business logic
 * Handles user registration, authentication, and profile management
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user in the system
     * @param user The user object with username, password, and phone
     * @return The registered user with userId
     */
    public User registerUser(User user) {
        // Hash the password before saving
        user.setPasswordHash(hashPassword(user.getPasswordHash()));
        user.setBalance(0.0);
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        return userRepository.save(user);
    }

    /**
     * Authenticate user with username and password
     * @param username The username
     * @param password The plain text password
     * @return The authenticated user or null if credentials are invalid
     */
    public User loginUser(String username, String password) {
        Optional<User> users = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username) 
                        && verifyPassword(password, u.getPasswordHash()))
                .findFirst();
        return users.orElse(null);
    }

    /**
     * Update user's current location
     * @param userId The user ID
     * @param lat The latitude
     * @param lng The longitude
     * @return The updated user object
     */
    public User updateLocation(@NonNull Long userId, Double lat, Double lng) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            u.setCurrentLat(lat);
            u.setCurrentLng(lng);
            u.setUpdatedAt(System.currentTimeMillis());
            return userRepository.save(u);
        }
        return null;
    }

    /**
     * Get user by ID
     * @param id The user ID
     * @return The user object or null if not found
     */
    public User getUserById(@NonNull Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * Hash password for storage
     */
    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    /**
     * Verify password against hash
     */
    private boolean verifyPassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }
}
