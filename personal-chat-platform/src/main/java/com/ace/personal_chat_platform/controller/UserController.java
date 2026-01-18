package com.ace.personal_chat_platform.controller;

import com.ace.personal_chat_platform.entity.User;
import com.ace.personal_chat_platform.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔍 SEARCH USERS BY USERNAME
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    // 👤 GET USER BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ✏️ UPDATE USER PROFILE
    @PutMapping("/{id}/profile")
    public User updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;

        user.setBio(updatedUser.getBio());
        user.setProfilePhoto(updatedUser.getProfilePhoto());

        return userRepository.save(user);
    }
}
