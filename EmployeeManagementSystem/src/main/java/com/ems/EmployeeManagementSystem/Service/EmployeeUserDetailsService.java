package com.ems.EmployeeManagementSystem.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.EmployeeManagementSystem.DTO.EmployeeEntity;
import com.ems.EmployeeManagementSystem.Entity.Employee;
import com.ems.EmployeeManagementSystem.Repository.EmployeeRepository;

@Service
public class EmployeeUserDetailsService
        implements UserDetailsService {

    private final EmployeeRepository repo;

    public EmployeeUserDetailsService(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            {

        EmployeeEntity emp = repo.findByEmail(email);
           /* .orElseThrow(() ->
                new UsernameNotFoundException("Employee not found"));*/

        return User.withUsername(emp.getEmail())
            .password(emp.getPassword()) // learning shortcut
            .roles(emp.getRole())
            .build();
    }
}

