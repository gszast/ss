package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Category;

public interface CategoryService {
    Category findByName(String name);
    Category save(Category category);
	public List<Category> findAll();
}
