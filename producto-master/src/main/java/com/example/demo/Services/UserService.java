package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.entity.Usuarios;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	 public UsuarioRepository reposiuser;
	

	
	//metodo para traernos un usuario con todos sus datos por medio de sus  username
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuarios usuarios = reposiuser.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado:" +username));
		return UserImpl.build(usuarios);
	}

	

}
