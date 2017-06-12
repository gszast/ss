package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Category;

public interface CategoryService {
    public Category findByName(String name);
    public Category save(Category category);
	public List<Category> findAll();
}
