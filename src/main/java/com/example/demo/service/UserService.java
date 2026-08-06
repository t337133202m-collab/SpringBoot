package com.example.demo.service;

import com.example.demo.dto.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser() {

        return new User(
                1L,
                "Tom",
                26);

    }
}