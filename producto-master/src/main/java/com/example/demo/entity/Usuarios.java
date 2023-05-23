package com.example.demo.entity;

import java.util.*;

import javax.validation.constraints.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Table;



@Entity
@Table(name ="usuarios", 
			uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
			})
public class Usuarios {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_usuario")
	private Long idUsuario;
	
	@NotBlank
	@Size(max = 20)
	private String username;
	 
	 @NotBlank
	  @Size(max = 50)
	  @Email
	private String email;
	
	@Column(name= "password")
	private String password;
	
	@Size(max = 100)
	private String nombres;
	  
	  
	  @Size(max = 100)
	  private String direccion;
	  
	  
	  @Size(max = 1)
	  private String status;
	  
	  
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascadetype.all para cuando se borra un usuario borre toda la informacion pertenecienciente a el //permite consultar usuario y me trae todo los roles
	@JoinTable(name= "usuarios_roles", 
				joinColumns = @JoinColumn(name = "usuario_id") ,
				
				inverseJoinColumns = @JoinColumn(name = "role_id"))
			
	private Set<Roles> roles = new HashSet<>();


	public Usuarios() {
		super();
	}


	public Usuarios(Long idUsuario, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, String password, @Size(max = 100) String nombres,
			@Size(max = 100) String direccion, @Size(max = 1) String status, Set<Roles> roles) {
		super();
		this.idUsuario = idUsuario;
		this.username = username;
		this.email = email;
		this.password = password;
		this.nombres = nombres;
		this.direccion = direccion;
		this.status = status;
		this.roles = roles;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Set<Roles> getRoles() {
		return roles;
	}


	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
}