package com.jeweleryguard.controller;

import com.jeweleryguard.model.Category;
import com.jeweleryguard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView saveCategory(@Valid Category category, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		categoryService.save(category);
		List<Category> categoryList = categoryService.findAll();
		modelAndView.addObject("categoryList",categoryList);
		modelAndView.addObject("successMessage", "Dodano do nową kategorie.");
		modelAndView.setViewName("admin/category");
		return modelAndView;
	}

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listCategories(){
		ModelAndView modelAndView = new ModelAndView();
		List<Category> categoryList = categoryService.findAll();
		modelAndView.setViewName("admin/category");
		modelAndView.addObject("category", new Category());
		modelAndView.addObject("categoryList", categoryList);
		return modelAndView;
	}


}
