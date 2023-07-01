package com.example.uis.dto.course;

import com.example.uis.dto.student.StudentQueryDTO;
import lombok.Data;

import java.util.Set;

@Data
public class CourseQueryDTO {

    private Integer id;
    private String name;
    private Integer semester;
    private Set<StudentQueryDTO> students;
}
