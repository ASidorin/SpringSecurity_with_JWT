package com.application.securityspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.securityspring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
