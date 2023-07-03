package com.example.uis.controllers;


import com.example.uis.dao.MaterialDao;
import com.example.uis.dto.material.MaterialCommandDTO;
import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<MaterialQueryDTO>> findAllByCourseId(@PathVariable("id") Integer id)
    {
        List<MaterialQueryDTO> materials = materialService.findAllByCourseId(id);
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }


//    @PostMapping
//    public ResponseEntity<Void> addMaterial(@RequestBody MaterialCommandDTO materialCommandDTO)
//    {
//        materialService.save(materialCommandDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping(value = "upload/{courseId}")
    public ResponseEntity<Void> upload(@RequestParam("photo") MultipartFile multipartFile ,
                                       @PathVariable("courseId") Integer courseId) throws IOException
    {
        materialService.upload(multipartFile, courseId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("id") Integer id) throws IOException {
        MaterialDao materialDao = materialService.download(id);
        ByteArrayResource byteArrayResource = new ByteArrayResource(materialDao.getFileBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("FILE_NAME" , materialDao.getName());

        return new ResponseEntity<>(byteArrayResource , httpHeaders, HttpStatus.OK);
    }

    @PutMapping("approve-review/{id}")
    public ResponseEntity<Void> review(@PathVariable("id") Integer id)
    {
        materialService.approveReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable("id") Integer id)
    {
        materialService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
