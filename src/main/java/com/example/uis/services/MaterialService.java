package com.example.uis.services;


import com.example.uis.dao.MaterialDao;
import com.example.uis.dto.material.MaterialCommandDTO;
import com.example.uis.dto.material.MaterialFileDTO;
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

    public List<Material> findAllUnapproved() {
        return materialRepository.findAllUnapproved();
    }

    public MaterialFileDTO upload(MultipartFile multipartFile, Integer id) throws IOException {
        String originalName = new Date().getTime() + "_" +  multipartFile.getOriginalFilename();

        String fullPath = baseFilePath + originalName;
        Path path = Paths.get(fullPath);
        Files.write(path, multipartFile.getBytes());

        return new MaterialFileDTO(fullPath, originalName);
    }

    public void save(MaterialCommandDTO materialCommandDTO) {
        Material material = new Material();
        material.setName(materialCommandDTO.getName());
        material.setDescription(materialCommandDTO.getDescription());
        material.setFullPath(materialCommandDTO.getFullPath());
        material.setFilename(materialCommandDTO.getFilename());
        material.setCourse(courseService.findById(materialCommandDTO.getCourseId()));

        materialRepository.save(material);
    }

    public MaterialDao download(Integer id) throws IOException {
        String fullPath = materialRepository.fullPathById(id);
        String filename = materialRepository.filenameById(id);

        Path path = Paths.get(fullPath);
        return new MaterialDao(Files.readAllBytes(path), filename);
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
