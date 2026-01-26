package com.ace.personal_chat_platform.service;

import com.ace.personal_chat_platform.dto.UserResponse;
import com.ace.personal_chat_platform.entity.User;

import java.util.List;

public interface UserService {

    UserResponse getMyProfile(String authHeader);

    UserResponse updateMyProfile(String authHeader, User updated);

    List<UserResponse> search(String username);

    UserResponse getById(Long id);

    List<UserResponse> getAll();
}
