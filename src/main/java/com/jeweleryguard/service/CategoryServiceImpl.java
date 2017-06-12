package com.jeweleryguard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeweleryguard.model.Category;
import com.jeweleryguard.repository.CategoryRepository;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

	@Override
	public List<Category> findAll() {
return categoryRepository.findAll();	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Autowired
	private CategoryRepository categoryRepository;
	
	

}
