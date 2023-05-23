package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.Table;



@Entity
@Table(name = "roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_Role")
	private Long idRole;
	
	
	@Column(name= "name")
	private String name;


	public Roles() {
		super();
	}


	public Roles(Long idRole, String name) {
		super();
		this.idRole = idRole;
		this.name = name;
	}


	public Long getIdRole() {
		return idRole;
	}


	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
