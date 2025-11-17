package com.ems.EmployeeManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.EmployeeManagementSystem.Entity.Skill;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long>{

}
