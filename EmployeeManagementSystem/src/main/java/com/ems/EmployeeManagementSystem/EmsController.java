package com.ems.EmployeeManagementSystem;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class EmsController {
 @Autowired
 private   EmployeeService employeeService ;

    //Dependency Injection
    // @Autowired
    // EmployeeService employeeService;
    
    @GetMapping("/test")
    public String test() {
    	return "Working";
    }
    

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployee();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.readEmployees(id);
    }

    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {
        // employees.add(employee);
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) 
            return "Employee Deleted";
        return "Not Found";
    }

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {     
        return employeeService.updateEmployee(id, employee);
    }
}
