package com.example.demo.controller;

import com.example.demo.dto.StudentRequest;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.lang.NonNull;
import jakarta.validation.Valid;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(
            StudentService service) {

        this.service = service;

    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return service.findAll();

    }

    @PostMapping("/students")
    public Student createStudent(
            @Valid @RequestBody StudentRequest request) {

        return service.save(request);

    }

    @PutMapping("/students/{id}")
    public Student update(
            @PathVariable Long id,
           @NonNull @RequestBody Student request) {
        request.setId(id);
        return service.update(request);

    }



    @GetMapping("/students/{id}")
    public Student getStudent(
            @NonNull @PathVariable Long id) {

        return service.findById(id);

    }

    @DeleteMapping("/students/{id}")
    public void delete(
            @NonNull @PathVariable Long id) {

        service.delete(id);

    }

    @PostMapping("/test-transaction")
    public String testTransaction() {

        service.createStudent();

        return "OK";
    }

    @GetMapping("/student")
    public String getStudent() {
        return service.getStudent();
    }


    @DeleteMapping("/student/deleteall")
    public void deleteAll() {
        service.refreshStudentCache();
    }

}