package com.ems.EmployeeManagementSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.EmployeeManagementSystem.DTO.EmployeeEntity;
import com.ems.EmployeeManagementSystem.Entity.Employee;
import com.ems.EmployeeManagementSystem.Entity.Skill;
import com.ems.EmployeeManagementSystem.ExceptionHandle.EmployeeNotFoundException;
import com.ems.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.ems.EmployeeManagementSystem.Repository.SkillsRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public String createEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();

		employeeEntity.setEmail(employee.getEmail());
		employeeEntity.setName(employee.getName());
		employeeEntity.setPhone(employee.getPhone());
		employeeEntity.setRole(employee.getRole());

		employeeEntity.setSkills(new ArrayList<Skill>());

		if (employee.getSkills() != null) {
			for (Skill s : employee.getSkills()) {

				Skill skillEntity = new Skill();

				skillEntity.setSkillName(s.getSkillName());
				skillEntity.setExp(s.getExp());

				skillEntity.setEmployee(employeeEntity);
				employeeEntity.getSkills().add(skillEntity);

			}
		}

		employeeRepository.save(employeeEntity);

		return "Employee saved";
	}

	@Override
	public Employee readEmployees(Long id) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);
		return employee;
	}

	@Override

	public List<Employee> readEmployee() {
		List<EmployeeEntity> employeesList = employeeRepository.findAll();
		List<Employee> employees = new ArrayList<>();

		for (EmployeeEntity employeeEntity : employeesList) {
			Employee emp = new Employee();
			emp.setId(employeeEntity.getId());
			emp.setName(employeeEntity.getName());
			emp.setEmail(employeeEntity.getEmail());
			emp.setPhone(employeeEntity.getPhone());
			emp.setSkills(employeeEntity.getSkills());
			employees.add(emp);

		}
		return employees;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		EmployeeEntity emp = employeeRepository.findById(id).get();
		employeeRepository.deleteById(emp.getId());
		return true;
	}

	@Override
    public String updateEmployee(Long id, Employee employee) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    boolean isAdmin=	auth.getAuthorities().stream()
    		.anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"));
    		
        EmployeeEntity existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee is not in DataBase"));

    	if(!existingEmployee.getEmail().equals(auth.getName()) && 	! isAdmin) {
    		throw new AccessDeniedException("You are not allowed to perform this action");
    	}
      // existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setRole(employee.getRole());

        
        employeeRepository.save(existingEmployee);
        return "Employee Updated";
    }

	@Override
	public List<Skill> getEmployeeSkillsbyID(Long id) {
		List<Skill> skill = new ArrayList<>();
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			EmployeeEntity employee = optional.get();
			skill = employee.getSkills();

		}
		return skill;
	}

	@Override
	public long count() {
		return employeeRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public Employee readEmployeeByEmail(String email) {
		EmployeeEntity existingEmployee = employeeRepository.findByEmail(email);
		if (existingEmployee == null) {
			Employee e = null;
			return e;
		}

		Employee employee = new Employee(); // <-- instantiate
		try {
			BeanUtils.copyProperties(existingEmployee, employee);
		} catch (Exception e) {

			logger.error("Failed to copy properties from EmployeeEntity to Employee", e);

		}
		return employee;
	}

	@Override
	@Transactional
	public String deleteByNameOrEmailOrPhone(String name, String email, String phone) {
		try {
			employeeRepository.deleteByNameOrEmailOrPhone(name, email, phone);
		} catch (Exception e) {
			e.printStackTrace();
			return "Not found";
		}

		return "Has been deleted";

	}

	@Override
	public List<Employee> saveMultipleEmployees(List<Employee> employees) {
		List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
		if (employees != null) {
			for (Employee employee : employees) {
				EmployeeEntity employeeEntity = new EmployeeEntity();
				employeeEntity.setEmail(employee.getEmail());
				employeeEntity.setPhone(employee.getPhone());
				employeeEntity.setName(employee.getName());

				if (employee.getSkills() != null) {
					for (Skill s : employee.getSkills()) {

						Skill skillEntity = new Skill();

						skillEntity.setSkillName(s.getSkillName());
						skillEntity.setExp(s.getExp());

						skillEntity.setEmployee(employeeEntity);
						employeeEntity.getSkills().add(skillEntity);

					}
				}
				employeeEntityList.add(employeeEntity);
			}

			try {
				employeeRepository.saveAllAndFlush(employeeEntityList);
			} catch (Exception e) {
				logger.error("Employees did not get saved", e);
			}
		}

		return readEmployee();
	}

	@Override
	public List<Employee> employeePagination(int offset, int limit, String sortBy) {
		Sort sort = Sort.by(sortBy).descending();
		PageRequest page = PageRequest.of(offset, limit, sort);

		List<EmployeeEntity> employeesList = employeeRepository.findAll(page).getContent();
		List<Employee> employees = new ArrayList<>();

		for (EmployeeEntity employeeEntity : employeesList) {
			Employee emp = new Employee();
			emp.setId(employeeEntity.getId());
			emp.setName(employeeEntity.getName());
			emp.setEmail(employeeEntity.getEmail());
			emp.setPhone(employeeEntity.getPhone());
			emp.setSkills(employeeEntity.getSkills());
			employees.add(emp);

		}
		return employees;

	}

}
