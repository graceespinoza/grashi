package com.example.demo.Services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonIgnore;



public class UserImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	

	private String username;
	 
	
	private String email;
	
	@JsonIgnore //el campo no se incluirá en la salida JSON 
	private String password;
	

	private String nombres;
	  
	  
	  private String direccion;
	  
	
	  private String status;
	
	
	private Collection<? extends GrantedAuthority>authorities; //esto es tipo como roles pero gracias al security se lo puede colocar de esa manera
		// TODO Auto-generated method stub
		



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	
	public UserImpl(Long idUsuario, String username, String email, String password, String nombres,
				String direccion, String status, Collection<? extends GrantedAuthority> authorities) {
			super();
			this.idUsuario = idUsuario;
			this.username = username;
			this.email = email;
			this.password = password;
			this.nombres = nombres;
			this.direccion = direccion;
			this.status = status;
			this.authorities = authorities;
		}
	public static UserImpl build(Usuarios usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
		.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return new UserImpl( //
			usuario.getIdUsuario(),
			usuario.getNombres(),
			usuario.getDireccion(),
			usuario.getEmail(),
			usuario.getUsername(),
			usuario.getPassword(),
			usuario.getStatus(),
			authorities);
			
				
	}

	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

}
