package com.example.uis.controllers;


import com.example.uis.dto.student.StudentQueryDTO;
import com.example.uis.entities.Student;
import com.example.uis.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "all")
    public ResponseEntity<List<StudentQueryDTO>> findAllStudents()
    {
        List<StudentQueryDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> addStudentToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId)
    {
        studentService.addStudentToCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> removeStudentFromCourse(@PathVariable Integer studentId, @PathVariable Integer courseId)
    {
        studentService.removeStudentFromCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
