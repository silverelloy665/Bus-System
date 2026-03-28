package com.busbooking.service;
import com.busbooking.dao.UserRepository;
import com.busbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@SuppressWarnings("null")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user) {
        if (user.getRole() == null) {
            user.setRole("CUSTOMER");
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> loginUser(String username, String passwordHash) {
        List<User> users = userRepository.findAll();
        return users.stream()
            .filter(u -> username.equals(u.getUsername()) && passwordHash.equals(u.getPasswordHash()))
            .findFirst();
    }
    public User updateLocation(Long userId, Double lat, Double lng) {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isPresent()) {
            User u = opt.get();
            u.setCurrentLat(lat);
            u.setCurrentLng(lng);
            return userRepository.save(u);
        }
        throw new RuntimeException("User not found");
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
