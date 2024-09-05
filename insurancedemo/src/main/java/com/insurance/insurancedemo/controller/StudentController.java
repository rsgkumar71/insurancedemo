package com.insurance.insurancedemo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.insurancedemo.model.Student;
import com.insurance.insurancedemo.service.StudentService;



@RestController
@RequestMapping("/student-record")
public class StudentController {

     private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Student> getStudent(@RequestParam("id") String studentID) {
        if (studentID != null) {
            int id = Integer.parseInt(studentID);
            Optional<Student> student = studentService.getStudent(id);
            if (student.isPresent()) {
                logger.info("Student found with ID: " + id);
                return ResponseEntity.ok(student.get());
            } else {
                logger.warn("Student not found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        }
        //logger.warn("Invalid student ID provided");
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Student> postStudent(@RequestParam("id") String studentID) {
        return getStudent(studentID);
    }
}