package com.busbooking.controller;

import com.busbooking.model.User;
import com.busbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody java.util.Map<String, String> payload) {
        Optional<User> user = userService.loginUser(payload.get("username"), payload.get("passwordHash"));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping
    public ResponseEntity<java.util.List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<User> updateLocation(@PathVariable Long id, @RequestParam Double lat, @RequestParam Double lng) {
        try {
            return ResponseEntity.ok(userService.updateLocation(id, lat, lng));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
