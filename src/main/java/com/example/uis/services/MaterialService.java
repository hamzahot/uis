package com.example.uis.services;


import com.example.uis.dao.MaterialDao;
import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.entities.Material;
import com.example.uis.mappers.MaterialMapper;
import com.example.uis.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class MaterialService {

    @Value("${fs.base-Url}")
    private String baseFilePath;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MaterialMapper materialMapper;


    public List<MaterialQueryDTO> findAllByCourseId(Integer id) {
        return materialRepository.findAllByCourseId(id).stream().map(materialMapper :: entityToQueryDto).toList();
    }

//    public void save(MaterialCommandDTO materialCommandDTO)
//    {
//        Course course = courseService.findById(materialCommandDTO.getCourseId());
//
//        Material material = materialMapper.commandDtoToEntity(materialCommandDTO);
//        material.setCourse(course);
//
//        materialRepository.save(material);
//    }

    public void upload(MultipartFile multipartFile, Integer courseId) throws IOException {
        String originalName = new Date().getTime() + "_" +  multipartFile.getOriginalFilename();

        String fullPath = baseFilePath + originalName;
        Path path = Paths.get(fullPath);
        Files.write(path, multipartFile.getBytes());

        Material material = new Material();
        material.setName(originalName);
        material.setFullPath(fullPath);

        Course course = new Course();
        course.setId(courseId);
        material.setCourse(course);

        materialRepository.save(material);
    }

    public MaterialDao download(Integer id) throws IOException {
        String fullPath = materialRepository.fullPathById(id);
        String name = materialRepository.nameById(id);


        Path path = Paths.get(fullPath);
        return new MaterialDao(Files.readAllBytes(path), name);
    }

    public void approveReview(Integer id) {
        Material material = materialRepository.findById(id).get();
        material.setIsReviewed(true);

        materialRepository.save(material);
    }

    public void deleteById(Integer id) {
        materialRepository.deleteById(id);
    }
}
