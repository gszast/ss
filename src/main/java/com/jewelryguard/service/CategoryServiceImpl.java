package com.jewelryguard.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelryguard.model.Category;
import com.jewelryguard.repository.CategoryRepository;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

	@Override
	public List<Category> findAll() {
		return Lists.newArrayList(categoryRepository.findAll());
	}

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
