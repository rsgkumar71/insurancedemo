package com.insurance.insurancedemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.insurance.insurancedemo.model.Student;


@Service
public class StudentService {

    public Optional<Student> getStudent(int id) {
        switch (id) {
            case 1:
                return Optional.of(new Student(1, "John", "Doe"));
            case 2:
                return Optional.of(new Student(2, "Jane", "Goodall"));
            case 3:
                return Optional.of(new Student(3, "Max", "Born"));
            default:
                return Optional.empty();
        }
    }
}
