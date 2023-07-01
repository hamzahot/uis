package com.example.uis.services;

import com.example.uis.dto.course.CourseCommandDTO;
import com.example.uis.dto.course.CourseQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.mappers.CourseMapper;
import com.example.uis.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public List<CourseQueryDTO> findAll() {
        return courseRepository.findAll().stream().map(courseMapper :: entityToCourseQueryDTO).toList();
    }

    public void save(CourseCommandDTO course) {
        courseRepository.save(courseMapper.courseCommandDtoToEntity(course));
    }

    public Course findById(Integer id){
        return courseRepository.findById(id).get();
    }
}
