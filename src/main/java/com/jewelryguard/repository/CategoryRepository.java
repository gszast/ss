package com.jewelryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Category;

@Repository(value = "CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Category findByName(String name);
}
