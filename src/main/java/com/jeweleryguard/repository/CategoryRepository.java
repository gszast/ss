package com.jeweleryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Category;

@Repository(value = "CategoryRepository")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{
	public Category findByName(String name);
}
