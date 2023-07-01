package com.example.uis.dto.student;


import com.example.uis.dto.role.RoleDTO;
import lombok.Data;

import java.util.Set;

@Data
public class StudentQueryDTO {

    private Integer id;
    private String fullName;
    private String index;
    private Set<RoleDTO> roles;
}
