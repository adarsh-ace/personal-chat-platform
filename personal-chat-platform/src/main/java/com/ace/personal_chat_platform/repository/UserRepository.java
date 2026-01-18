package com.ace.personal_chat_platform.repository;

import com.ace.personal_chat_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    // 🔍 SEARCH USERS
    List<User> findByUsernameContainingIgnoreCase(String username);
}
