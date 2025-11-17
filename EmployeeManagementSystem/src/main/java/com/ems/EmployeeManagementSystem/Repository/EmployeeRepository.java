package com.ems.EmployeeManagementSystem.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.EmployeeManagementSystem.DTO.EmployeeEntity;



@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  EmployeeEntity findByEmail(String email);
  void deleteByNameOrEmailOrPhone(String name , String email, String phone);
  
	
}
