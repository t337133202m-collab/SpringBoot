package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        if ("admin".equals(request.getUsername())
                && "1234".equals(request.getPassword())) {

            return jwtUtil.generateToken(
                    request.getUsername());
        }

        throw new RuntimeException(
                "帳號或密碼錯誤");
    }
}