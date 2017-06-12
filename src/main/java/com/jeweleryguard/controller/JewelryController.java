package com.jeweleryguard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jeweleryguard.model.Category;
import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import com.jeweleryguard.service.CategoryService;
import com.jeweleryguard.service.JewelryService;
import com.jeweleryguard.service.UserService;

@Controller
@RequestMapping(value = "/jewelrys")
public class JewelryController {

	@Autowired
	private UserService userService;
	@Autowired
	private JewelryService jewelryService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newJewelry(){
		ModelAndView modelAndView = new ModelAndView();
		Jewelry jewelry = new Jewelry();
		List<Category> categoryList = categoryService.findAll();
		modelAndView.addObject(categoryList);
		modelAndView.addObject("jewelry", jewelry);
		modelAndView.setViewName("jewelrys/new");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView newJewelry(@Valid Jewelry jewelry, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		jewelry.setUser(user);
		if (bindingResult.hasErrors()) {
		} else {
			jewelryService.saveJewelry(jewelry);
			modelAndView.addObject("successMessage", "Dodano do biblioteki.");
			modelAndView.addObject("jewelrys", new Jewelry());

		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Jewelry> jewelryList = user.getJewelryList();
		modelAndView.addObject("jewelry", jewelryList );
		modelAndView.setViewName("jewelrys/list");
		return modelAndView;
	}


}
