package com.ems.EmployeeManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.EmployeeManagementSystem.Entity.Employee;
import com.ems.EmployeeManagementSystem.Entity.Skill;

public interface EmployeeService {
    String createEmployee(Employee employee);
    List<Employee> readEmployee();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
    Employee readEmployees(Long id);
    
    List<Skill> getEmployeeSkillsbyID(Long id);
    long count();
    Employee  readEmployeeByEmail(String email) ;
    
    //Employee createEmployeeWithSkills(Employee e);
    String deleteByNameOrEmailOrPhone(String name , String email, String phone);
    List<Employee> saveMultipleEmployees(List<Employee> employees);
}
