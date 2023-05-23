package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Services.UserService;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Usuarios;
import com.example.demo.logica_Services.Usuario_Logica;

public class UserController {
	
	@Autowired
	private Usuario_Logica usuarioLogica;
	
	@GetMapping
	 public List<Usuarios> listar(){
		return usuarioLogica.enlistar();
	}
	
	@GetMapping("/rol")
	public List<Roles> getRoles(){
		return usuarioLogica.enlistarRoles();
	}
	@GetMapping("/{id}")
	public Usuarios getUsuarioById(@PathVariable("id") Long id) {
		return usuarioLogica.listarById(id);
	}
	//editar
	@PostMapping
	public ResponseEntity<?> insertar(@RequestBody Usuarios userBody){
		
		return usuarioLogica.insertar(userBody);
	}
	
	@PutMapping("/editar/{id}")
	public Usuarios actualizar(@PathVariable Long id, @RequestBody Usuarios userBody) {
		userBody.setIdUsuario(id);
		return usuarioLogica.actualizar(userBody);
	}
	
	@PutMapping("/eliminar/{id}")
	public List<Usuarios> eliminar(@PathVariable Long id){
		return usuarioLogica.eliminar(id);
	}
}