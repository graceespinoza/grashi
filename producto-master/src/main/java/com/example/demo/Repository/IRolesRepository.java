package com.example.demo.Repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Roles;


public interface IRolesRepository extends JpaRepository<Roles, Long> {
	
	@Query("SELECT o FROM Roles o ORDER BY o.id ASC")
	public List<Roles> findAllRoles();
	
	@Query("SELECT o FROM Roles o WHERE o.name = 'ROLE_USER'")
	public Set<Roles> getRoleUser();
 //metodo para buscar un rol por su nombre en nuetsra bd
}
