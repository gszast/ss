package com.jewelryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Category;

@Repository(value = "CategoryRepository")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{
	public Category findByName(String name);
}
