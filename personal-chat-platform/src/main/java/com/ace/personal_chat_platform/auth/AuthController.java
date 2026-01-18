package com.ace.personal_chat_platform.auth;

import com.ace.personal_chat_platform.entity.User;
import com.ace.personal_chat_platform.repository.UserRepository;
import com.ace.personal_chat_platform.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Registered successfully");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        Optional<User> userOpt =
                userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(
                loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(token);

    }
}
