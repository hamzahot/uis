package com.example.uis.security.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthenticatedHandler implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        UnauthenticatedDTO unauthenticatedDTO = new UnauthenticatedDTO(
//                "Access Denied", authException.getMessage()
//        );
//
//        response.setStatus(401);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().write(objectMapper.writeValueAsString(unauthenticatedDTO));
    }
}
