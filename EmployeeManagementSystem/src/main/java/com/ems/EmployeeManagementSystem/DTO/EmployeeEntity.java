package com.ems.EmployeeManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

import com.ems.EmployeeManagementSystem.Entity.Skill;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="ems_tb")
@ToString(exclude = "skills")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
  
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
     private List<Skill> skills = new ArrayList<>();
    
    @Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email  + "]";
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
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
	
}
