package com.aws.controller;

import com.aws.entity.Student;
import com.aws.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student")
    public Student saveStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable String studentId){
        Student student = studentRepository.getStudentById(studentId);
        return student;
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Student> updateStudentById(@PathVariable String studentId, @RequestBody Student student){
        try {
            studentRepository.updateStudentById(studentId, student);
            return ResponseEntity.ok(student);
        } catch (RuntimeException e){
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(student);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public String deleteStudentById(@PathVariable String studentId){
        studentRepository.deleteStudentById(studentId);
        return "Student Deleted";
    }
}
