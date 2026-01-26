package com.ace.personal_chat_platform.service;

import com.ace.personal_chat_platform.dto.UserResponse;
import com.ace.personal_chat_platform.entity.User;
import com.ace.personal_chat_platform.repository.UserRepository;
import com.ace.personal_chat_platform.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔐 get current user from token
    private User getUserFromToken(String authHeader) {
        String token = authHeader.substring(7);
        String email = JwtUtil.extractEmail(token);
        return userRepository.findByEmail(email).orElse(null);
    }

    // 👤 my profile
    @Override
    public UserResponse getMyProfile(String authHeader) {
        User user = getUserFromToken(authHeader);
        return user == null ? null : UserResponse.from(user);
    }

    // ✏️ update profile
    @Override
    public UserResponse updateMyProfile(String authHeader, User updated) {
        User user = getUserFromToken(authHeader);
        if (user == null) return null;

        user.setBio(updated.getBio());
        user.setProfilePhoto(updated.getProfilePhoto());
        user.setLastSeen(LocalDateTime.now());

        return UserResponse.from(userRepository.save(user));
    }

    // 🔍 search users
    @Override
    public List<UserResponse> search(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username)
                .stream().map(UserResponse::from).toList();
    }

    // 👁 public profile
    @Override
    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElse(null);
    }

    // 🧪 testing
    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream().map(UserResponse::from).toList();
    }
}
