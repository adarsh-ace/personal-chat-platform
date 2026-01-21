package com.ace.personal_chat_platform.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository
        extends JpaRepository<BlacklistedToken, Long> {

    boolean existsByToken(String token);
}
