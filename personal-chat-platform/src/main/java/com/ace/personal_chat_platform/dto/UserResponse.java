package com.ace.personal_chat_platform.dto;

import com.ace.personal_chat_platform.entity.User;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String profilePhoto;
    private String bio;
    private boolean online;
    private LocalDateTime lastSeen;

    // 🔹 Static mapper
    public static UserResponse from(User user) {
        UserResponse dto = new UserResponse();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.profilePhoto = user.getProfilePhoto();
        dto.bio = user.getBio();
        dto.online = user.isOnline();
        dto.lastSeen = user.getLastSeen();
        return dto;
    }

    // 🔹 Getters only

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getProfilePhoto() { return profilePhoto; }
    public String getBio() { return bio; }
    public boolean isOnline() { return online; }
    public LocalDateTime getLastSeen() { return lastSeen; }
}
