package com.ems.EmployeeManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.ems.EmployeeManagementSystem.DTO.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String skillName;
	private int exp;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "employee_id")  
	private EmployeeEntity employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public Skill(int id, String skillName, int exp, EmployeeEntity employee) {
		super();
		this.id = id;
		this.skillName = skillName;
		this.exp = exp;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillName=" + skillName + ", exp=" + exp + ", employee=" + employee + "]";
	}

	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
