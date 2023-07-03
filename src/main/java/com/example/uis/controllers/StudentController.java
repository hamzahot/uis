package com.example.uis.controllers;


import com.example.uis.dto.student.StudentCreateDTO;
import com.example.uis.dto.student.StudentQueryDTO;
import com.example.uis.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody StudentCreateDTO studentCreateDTO){

        String password = studentCreateDTO.getPassword();
        studentCreateDTO.setPassword(passwordEncoder.encode(password));
        studentService.insert(studentCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<StudentQueryDTO>> findAllStudents()
    {
        List<StudentQueryDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("course/{courseId}")
    public ResponseEntity<List<StudentQueryDTO>> findAllByCourseId(@PathVariable("courseId") Integer courseId)
    {
        List<StudentQueryDTO> students = studentService.findAllByCourseId(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("addStudent/{studentId}/{courseId}")
    public ResponseEntity<Void> addStudentToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId)
    {
        studentService.addStudentToCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("removeStudent/{studentId}/{courseId}")
    public ResponseEntity<Void> removeStudentFromCourse(@PathVariable Integer studentId, @PathVariable Integer courseId)
    {
        studentService.removeStudentFromCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate-student/{id}")
    public ResponseEntity<Void> deactivateStudent(@PathVariable("id") Integer id)
    {
        studentService.deactivateStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
