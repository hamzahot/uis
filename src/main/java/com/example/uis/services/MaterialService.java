package com.example.uis.services;


import com.example.uis.dto.material.MaterialCommandDTO;
import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.entities.Material;
import com.example.uis.mappers.MaterialMapper;
import com.example.uis.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MaterialMapper materialMapper;


    public List<MaterialQueryDTO> findAllByCourseId(Integer id) {
        return materialRepository.findAllByCourseId(id).stream().map(materialMapper :: entityToQueryDto).toList();
    }

    public void save(MaterialCommandDTO materialCommandDTO)
    {
        Course course = courseService.findById(materialCommandDTO.getCourseId());

        Material material = materialMapper.commandDtoToEntity(materialCommandDTO);
        material.setCourse(course);

        materialRepository.save(material);
    }
}
