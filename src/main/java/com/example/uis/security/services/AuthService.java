package com.example.uis.security.services;


import com.example.uis.dto.student.StudentCreateDTO;
import com.example.uis.mappers.StudentMapper;
import com.example.uis.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    private final PasswordEncoder passwordEncoder;


    public void save(StudentCreateDTO studentCreateDTO)
    {
        String password = studentCreateDTO.getPassword();
        studentCreateDTO.setPassword(passwordEncoder.encode(password));

        studentService.insert(studentCreateDTO);
    }


}
