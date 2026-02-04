package com.ems.EmployeeManagementSystem.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotEmpty
    private String name;
	@NotNull
    private String phone;
	@NotEmpty
	private String role;
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
	@Email
	@Column(nullable = false,unique = true)
	private String email;
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	

}
