package com.example.demo.logica_Services;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.IRolesRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Usuarios;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.payload.response.MessageResponse;

@Service
public class Usuario_Logica{
	
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private IRolesRepository rolesRepository;
	
	@Autowired
	private PasswordEncoder enconder;
	
	public ResponseEntity<?> insertar(Usuarios usuario){
		
		if(verificacion_datos(usuario).getStatusCode().is4xxClientError()) {
			return verificacion_datos(usuario);
		}
		usuario.setPassword(enconder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
		
	}
	public Usuarios actualizar (Usuarios usuarios){
		Optional<Usuarios> optional = usuarioRepository.findById(usuarios.getIdUsuario());
		if(optional.isEmpty()) {
			return null;
		}
		Usuarios editar = optional.get();
		CamposNulos(usuarios,editar);
		return usuarioRepository.save(editar);
		
	}
	public List<Usuarios>enlistar(){
		return usuarioRepository.findByEstado();
		
	}
	public List<Roles>enlistarRoles(){
		return rolesRepository.findAllRoles();
	}
	public Usuarios listarById(Long id) {
		return usuarioRepository.findById(id).get();
	}
	public List<Usuarios> eliminar(Long id){
		usuarioRepository.deleteById(id);
		return usuarioRepository.findByEstado();
	}
	
	
	public ResponseEntity<?>registrar(@Valid Usuarios singupRequest){
		if(verificacion_datos(singupRequest).getStatusCode().is4xxClientError()) {
			return verificacion_datos(singupRequest);
		}
		Set<Roles> rol = rolesRepository.getRoleUser();
		singupRequest.setRoles(rol);
		singupRequest.setStatus("A");
		singupRequest.setPassword(enconder.encode(singupRequest.getPassword()));
		usuarioRepository.save(singupRequest);
		return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
	}
	//verifica si los campos son nuloss
	public void CamposNulos (Usuarios obtener, Usuarios  destino) {
		
		if(obtener.getNombres() !=null) {
			destino.setNombres(obtener.getNombres());
		}
		if(obtener.getEmail() !=null) {
			destino.setEmail(obtener.getEmail());
		}
		if(obtener.getDireccion() !=null) {
			destino.setDireccion(obtener.getDireccion());
		}
		if(obtener.getPassword() !=null) {
			destino.setPassword(enconder.encode(obtener.getPassword()));
		}
		if(obtener.getRoles().size() > 0) {
			destino.setRoles(obtener.getRoles());
		}
		if (obtener.getStatus() !=null) {
			destino.setStatus(obtener.getStatus());
		}
		
	}
	//verifica si email o suaurio existe y si tiene campos vacios
	private ResponseEntity<?> verificacion_datos(Usuarios usuarios){
		if(usuarioRepository.existsByUsername(usuarios.getUsername())){
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Usuario utilizado"));
		}
		if(usuarioRepository.existsByEmail(usuarios.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("WARNING: Email ya es utilizado"));
			
		}//esto me permite verificar si es nullo o no 
		if(usuarios.getUsername()  == null || usuarios.getEmail()   == null ||
		   usuarios.getDireccion() == null || usuarios.getNombres() == null ||
		   usuarios.getPassword()  == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Warning:Campos Vacios!"));
		}
		return ResponseEntity.ok(new MessageResponse("Usuarios registrado correctamente!!!"));
	}
	
	
}