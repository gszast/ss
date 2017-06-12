package com.jeweleryguard.controller;

import com.jeweleryguard.model.*;
import com.jeweleryguard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jewelrys")
public class JewelryController {

	@Autowired
	private UserService userService;
	@Autowired
	private JewelryService jewelryService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MetalService metalService;
	@Autowired
	private MyFileService myFileService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newJewelry(){
		ModelAndView modelAndView = new ModelAndView();
		Jewelry jewelry = new Jewelry();
		List<Category> categoryList = categoryService.findAll();
		modelAndView.addObject("categoryList", categoryList);

		List<Metal> metalList = metalService.findAll();
		modelAndView.addObject("metalList", metalList);

		List<MyFile> fileList = myFileService.findAll();
		modelAndView.addObject("files", fileList);

		modelAndView.addObject("jewelry", jewelry);
		modelAndView.setViewName("jewelrys/new");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String newJewelry(@Valid Jewelry jewelry, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		jewelry.setUser(user);
		if (bindingResult.hasErrors()) {
			bindingResult
					.rejectValue("name", "error.jewelry",
							"Podaj nazwe");
		} else {
			jewelryService.saveJewelry(jewelry);
			List<Jewelry> jewelryList = jewelryService.findAll();
			modelAndView.addObject("successMessage", "Dodano do biblioteki.");
			modelAndView.addObject("jewelryList", jewelryList);

			modelAndView.setViewName("jewelrys");

		}
		return "redirect:jewelrys";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Jewelry> jewelryList = user.getJewelryList();
		modelAndView.addObject("jewelryList", jewelryList );
		modelAndView.setViewName("jewelrys");
		return modelAndView;
	}


}
