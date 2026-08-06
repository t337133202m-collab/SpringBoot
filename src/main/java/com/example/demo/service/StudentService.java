package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(
            StudentRepository repository) {

        this.repository = repository;
    }

    public List<Student> findAll() {

        return repository.findAll();

    }

    public Student save(@NonNull Student student) {

        return repository.save(student);

    }

    public Student save(StudentRequest request) {

        if (request.getAge() < 18) {

            throw new StudentNotFoundException("Age must be greater than 18");
        }

        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());

        return repository.save(student);
    }

    @Cacheable(value = "student", key = "#id")
    public Student findById(@NonNull Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException(
                                "Student Not Found:" + id));

    }

    @CacheEvict(
            value = "student",
            key = "#id"
    )
    public void delete(@NonNull Long id) {

        repository.deleteById(id);

    }

    @Transactional
    public void createStudent() {

        Student student = new Student();

        student.setName("Tom");
        student.setAge(20);

        repository.save(student);

        throw new RuntimeException("force rollback");

    }

    public String getStudent() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Tom";
    }


    @CachePut(
        value = "student",
        key = "#student.id"
    )
    public Student update(@NonNull Student student) {

        return repository.save(student);
    }

    @CacheEvict(
        value = "student",
        allEntries = true
    )
    public void refreshStudentCache() {

    }
}