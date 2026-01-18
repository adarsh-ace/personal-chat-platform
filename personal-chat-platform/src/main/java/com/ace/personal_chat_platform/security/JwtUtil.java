package com.ace.personal_chat_platform.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "ACE_SUPER_SECRET_KEY_123456789_ACE";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private static final Key SECRET_KEY =
            new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    // ✅ Generate JWT
    public static String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // ✅ Extract email
    public static String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // ✅ Validate token
    public static boolean isTokenValid(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
