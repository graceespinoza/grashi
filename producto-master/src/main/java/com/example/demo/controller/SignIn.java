package com.example.demo.controller;

import java.util.stream.Collectors;
import java.util.*;
import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.UserImpl;
import com.example.demo.entity.Usuarios;
import com.example.demo.logica_Services.Usuario_Logica;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.securityJwt.JwtUtils;

@CrossOrigin()
@RestController
@RequestMapping("/app/login")
public class SignIn {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	Usuario_Logica userLogica;
	
	@Autowired
	JwtUtils jwtU;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtU.generateJwtToken(authentication);
		
		UserImpl userImpl = (UserImpl) authentication.getPrincipal();
		List<String> roles = userImpl.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
								userImpl.getIdUsuario(),
								userImpl.getUsername(),
								userImpl.getPassword(),
								roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuarios usuarioregistrado){
		ResponseEntity<?> repon = userLogica.registrar(usuarioregistrado);
		return repon;
	}
}
