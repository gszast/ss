package com.jewelryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Role;

@Repository(value = "RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}
