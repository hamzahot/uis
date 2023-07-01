package com.example.uis.controllers;


import com.example.uis.dto.material.MaterialCommandDTO;
import com.example.uis.dto.material.MaterialQueryDTO;
import com.example.uis.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> addMaterial(@RequestBody MaterialCommandDTO materialCommandDTO)
    {
        materialService.save(materialCommandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
