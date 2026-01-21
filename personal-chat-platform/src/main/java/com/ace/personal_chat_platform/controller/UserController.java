package com.ace.personal_chat_platform.controller;

import com.ace.personal_chat_platform.entity.User;
import com.ace.personal_chat_platform.repository.UserRepository;
import com.ace.personal_chat_platform.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ GET ALL USERS (testing)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔍 SEARCH USERS BY USERNAME
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    // 👤 GET MY PROFILE (JWT)
    @GetMapping("/me")
    public User getMyProfile(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = JwtUtil.extractEmail(token);

        return userRepository.findByEmail(email).orElse(null);
    }

    // ✏️ UPDATE MY PROFILE (JWT)
    @PutMapping("/me")
    public User updateMyProfile(@RequestHeader("Authorization") String authHeader,
                                @RequestBody User updatedUser) {

        String token = authHeader.substring(7);
        String email = JwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        user.setBio(updatedUser.getBio());
        user.setProfilePhoto(updatedUser.getProfilePhoto());
        user.setLastSeen(LocalDateTime.now());

        return userRepository.save(user);
    }

    // 👤 GET USER BY ID (public preview)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
