package com.example.demo.payload.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class SignUpRequest {
	
	@NotBlank
	@Size(min =3, max=20 )
	private String username;
	
	@NotBlank
	 @Size(max =50)
	private String email;
	
	private Set<String> roles;
	
	
	@Size(min = 6, max =40)
	@Column(name= "password")
	private String password;
	
	@Size(max = 100)
	private String nombres;
	  
	  
	  @Size(max = 100)
	  private String direccion;
	  
	  
	  @Size(max = 1)
	  private String status;


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


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
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
	  
	  
	  

}
