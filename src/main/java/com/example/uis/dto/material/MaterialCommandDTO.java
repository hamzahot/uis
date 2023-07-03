package com.example.uis.dto.material;


import lombok.Data;

@Data
public class MaterialCommandDTO {

    private Integer courseId;
    private String name;
    private String fullPath;
    private String description;
    private String filename;
}
