package com.ems.EmployeeManagementSystem.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.ems.EmployeeManagementSystem.Entity.Employee;
import com.ems.EmployeeManagementSystem.Entity.Skill;
import com.ems.EmployeeManagementSystem.ExceptionHandle.WrongInputException;
import com.ems.EmployeeManagementSystem.Service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/emp")  
public class EmsController {
	private Logger logger = LoggerFactory.getLogger( EmsController.class);
	
 @Autowired
 private   EmployeeService employeeService ;
 

    
    @GetMapping("Ding")
    public String ding() {
    	logger.info("This is Working");
    	return "Dong!";
    }
    

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
    List<Employee> list =	employeeService.readEmployee();
    if (list ==null) {
    	throw new NoSuchElementException();
    }
        return list;
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.readEmployees(id);
    }

    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {
    	
    	if( employee.getName()== null || employee.getName().isEmpty() || employee.getName().isBlank() ) {
    		throw new WrongInputException("Name should not be empty");
    	}
    	else if(employee.getEmail() == null || employee.getEmail().isBlank() || employee.getEmail().isEmpty() ) {
    		throw new WrongInputException("Email should not be empty");
    	}
    	
        logger.info("employee: {}" , employee);
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        try{employeeService.deleteEmployee(id) ;
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e) {
        return ResponseEntity.ok("Not Found");
    }}

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {     
        return employeeService.updateEmployee(id, employee);
    }
    
    @GetMapping("skills/{id}")
    public ResponseEntity< List<Skill>> getSkills (@PathVariable Long id ){
    	if ( employeeService.getEmployeeSkillsbyID(id).size()==0) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	else {
    	return ResponseEntity.ok(employeeService.getEmployeeSkillsbyID(id));
    }}
    
    @GetMapping("count")
    public long count ( ){
    	return employeeService.count();
    }
    

    @GetMapping("employeess/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) {
    	
        Employee employee = employeeService.readEmployeeByEmail(email);
       
        if (employee == null) {
            throw new NoSuchElementException("Employee with email " + email + " not found");
        }
        	return employee;
        
    }
    
    @DeleteMapping("deleteEmployee")
    public String  delete( @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) 
    {
    	logger.info("These are  values  email:{}, phone :{}",email,phone);
    	return employeeService.deleteByNameOrEmailOrPhone(name, email, phone);
    	
    }
    
    
    
    @PostMapping("allEmployees")
    public ResponseEntity<List<Employee>> saveMultipleEmployee( @RequestBody List<Employee> employees){
    	
    	try{
    		
    				return ResponseEntity.ok(employeeService.saveMultipleEmployees(employees));
    		}
    	catch(Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();}
    	
         
    	
    }
    
    @GetMapping("employeePages")
    public ResponseEntity<List<Employee>> getEmployeePages(@RequestParam(required=false,defaultValue="1") int offset,@RequestParam(required=false,defaultValue="10") int limit  ,@RequestParam(required=false,defaultValue="Id") String sortBy   )
    {
    	offset-=1;
    	try {return   ResponseEntity.ok(employeeService.employeePagination(offset,limit,sortBy));
    	
    	}catch(Exception e) {
    		return ResponseEntity.noContent().build();
    	}
    	
    }
    
}
