package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.Category;

public interface CategoryService {
    Category findByName(String name);
    Category save(Category category);
	public List<Category> findAll();
}
