package com.busbooking.controller;

import com.busbooking.model.User;
import com.busbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String username, @RequestParam String passwordHash) {
        Optional<User> user = userService.loginUser(username, passwordHash);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).build();
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
