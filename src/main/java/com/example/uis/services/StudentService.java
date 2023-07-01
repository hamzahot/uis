package com.example.uis.services;


import com.example.uis.dto.student.StudentQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.entities.Student;
import com.example.uis.mappers.StudentMapper;
import com.example.uis.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentQueryDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(studentMapper :: entityToQueryDTO).toList();
    }

    public void addStudentToCourse(Integer studentId, Integer courseId)
    {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseService.findById(courseId);

        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public void removeStudentFromCourse(Integer studentId, Integer courseId)
    {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseService.findById(courseId);

        student.getCourses().remove(course);
        studentRepository.save(student);
    }

    public Student findById(Integer id){
        return studentRepository.findById(id).get();
    }
}
