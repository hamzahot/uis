package com.example.uis.dto.student;


import com.example.uis.dto.role.RoleDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentCreateDTO {

    private String fullName;
    private String indeks;
    private String username;
    private String password;
    private Boolean isDeactivated;
    private List<RoleDTO> roles;
}
