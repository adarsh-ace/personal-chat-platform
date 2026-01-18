package com.ace.personal_chat_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/secure")
    public String secure() {
        return "You are AUTHENTICATED with JWT 😈";
    }
}
