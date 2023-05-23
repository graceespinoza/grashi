package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuarios;



public interface UsuarioRepository extends JpaRepository<Usuarios, Long> { 
	
	//metodo para poder buscar un usuario mediante su nombre
	Optional<Usuarios> findByUsername(String username); 
	
	//metodo para poder verificar si un usuario existe en nuestra bd 
	Boolean existsByUsername(String username);
	
	
	//metodo para poder verificar si un usuario existe en nuestra bd 
		Boolean existsByEmail(String email);
		
	@Modifying
	@Query("UPDATE Usuarios a SET a.status = null WHERE a.id = ?1")
	public void deleteById(Long id);
	
	@Query("SELECT a FROM Usuarios a WHERE a.status != null ORDER BY a.id ASC")
	public List<Usuarios> findByEstado();
}
