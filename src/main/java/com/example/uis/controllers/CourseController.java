package com.example.uis.controllers;


import com.example.uis.dto.course.CourseCommandDTO;
import com.example.uis.dto.course.CourseQueryDTO;
import com.example.uis.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "all")
    public ResponseEntity<List<CourseQueryDTO>> findAllCourses()
    {
        List<CourseQueryDTO> courses = courseService.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody CourseCommandDTO course)
    {
        courseService.save(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
