package com.example.uis.dao;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaterialDao {

    private byte[] fileBytes;
    private String name;
}
