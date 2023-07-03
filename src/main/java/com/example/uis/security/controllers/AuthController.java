package com.example.uis.security.controllers;


import com.example.uis.dto.student.StudentCreateDTO;
import com.example.uis.security.dto.LoginDTO;
import com.example.uis.security.dto.TokenDTO;
import com.example.uis.security.jwt.JwtTokenProvider;
import com.example.uis.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
public class AuthController {


    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManagerBean;

    private final AuthService authService;


    @PostMapping(value = "login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO)
    {
        try
        {
            Authentication authData = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword() // client password | User (Spring Security Code -> DB password)
            );
            Authentication authentication = authenticationManagerBean.authenticate(authData);

            // username
            // password
            // authorities (role)
            SecurityContextHolder.getContext().setAuthentication(authentication); // context holder filled!

            TokenDTO tokenDTO = tokenProvider.generateToken(authentication, loginDTO.isRememberMe());
            return new ResponseEntity<>(tokenDTO, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bad credentials!");
            response.put("exceptionMessage", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping(value = "register")
    public ResponseEntity<Void> register(@RequestBody StudentCreateDTO studentCreateDTO)
    {
        authService.save(studentCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
