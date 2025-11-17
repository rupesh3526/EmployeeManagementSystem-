package com.ems.EmployeeManagementSystem.Entity;

import java.util.List;

import jakarta.persistence.Id;





public class Employee {
	@Id
    private Long id;
    private String name;
    private String phone;
    private List<Skill> skills;
    public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills (List<Skill> skills) {
		this.skills = skills;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;

}
