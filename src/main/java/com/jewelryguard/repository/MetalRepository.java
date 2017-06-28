package com.jewelryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Metal;

@Repository("MetalRepository")
public interface MetalRepository extends JpaRepository<Metal, Integer>{
	public Metal findByName(String name);
}
