package com.jeweleryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Role;

@Repository(value = "RoleRepository")
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>{
	Role findByRole(String role);

}
