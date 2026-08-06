package com.example.demo.controller;

import com.example.demo.config.SchoolProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    private final SchoolProperties schoolProperties;

    public ConfigController(
            SchoolProperties schoolProperties) {

        this.schoolProperties = schoolProperties;
    }

    @GetMapping("/school")
    public SchoolProperties school() {

        return schoolProperties;

    }
}