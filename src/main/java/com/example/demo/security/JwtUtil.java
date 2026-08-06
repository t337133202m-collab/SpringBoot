package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

        private static final String SECRET = "mysecretkeymysecretkeymysecretkey123";

        private final SecretKey secretKey = Keys.hmacShaKeyFor(
                        SECRET.getBytes(StandardCharsets.UTF_8));

        public String generateToken(String username) {

                return Jwts.builder()
                                .subject(username)
                                .issuedAt(new Date())
                                .expiration(
                                                new Date(
                                                                System.currentTimeMillis()
                                                                                + 1000 * 60 * 60))
                                .signWith(secretKey)
                                .compact();
        }

        public String extractUsername(String token) {

                return Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload()
                                .getSubject();
        }
}