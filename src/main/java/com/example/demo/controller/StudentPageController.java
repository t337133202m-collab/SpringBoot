package com.example.demo.controller;


import com.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;




@Controller
public class StudentPageController {

    private final StudentRepository repository;

    public StudentPageController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/student-page")
    public String studentPage(Model model) {

        model.addAttribute(
                "students",
                repository.findAll());

        return "students";
    }
}