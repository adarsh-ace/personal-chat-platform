package com.ace.personal_chat_platform.websocket;

import com.ace.personal_chat_platform.entity.User;
import com.ace.personal_chat_platform.repository.UserRepository;
import com.ace.personal_chat_platform.security.JwtUtil;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class WebSocketPresenceListener {

    private final UserRepository userRepository;

    public WebSocketPresenceListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ CONNECT → ONLINE
    @EventListener
    public void handleWebSocketConnect(SessionConnectEvent event) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String authHeader = accessor.getFirstNativeHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String email = JwtUtil.extractEmail(token);

            Optional<User> userOpt = userRepository.findByEmail(email);
            userOpt.ifPresent(user -> {
                user.setOnline(true);
                user.setLastSeen(LocalDateTime.now());
                userRepository.save(user);
            });
        }
    }

    // ✅ DISCONNECT → OFFLINE
    @EventListener
    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String authHeader = accessor.getFirstNativeHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String email = JwtUtil.extractEmail(token);

            Optional<User> userOpt = userRepository.findByEmail(email);
            userOpt.ifPresent(user -> {
                user.setOnline(false);
                user.setLastSeen(LocalDateTime.now());
                userRepository.save(user);
            });
        }
    }
}
