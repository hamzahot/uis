package com.example.uis.dto.material;


import com.example.uis.dto.course.CourseQueryDTO;
import lombok.Data;

@Data
public class MaterialQueryDTO {

    private Integer id;
    private String name;
    private String fullPath;
    private String description;
    private Boolean isReviewed;
}
