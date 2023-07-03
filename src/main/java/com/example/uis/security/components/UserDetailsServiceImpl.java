package com.example.uis.security.components;


import com.example.uis.entities.Role;
import com.example.uis.entities.Student;
import com.example.uis.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private StudentRepository studentRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findWithRolesByUsername(username); // unique
        if (student != null)
        {
            Set<Role> roles = student.getRoles(); // authorities <=> roles
            Collection<? extends GrantedAuthority> authorities = roles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();

            return new org.springframework.security.core.userdetails.User(
                    student.getUsername(),
                    student.getPassword(),
                    authorities
            );
        }
        else {
            throw new UsernameNotFoundException("User with username " + username + " not found!");
        }
    }



}
