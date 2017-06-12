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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jeweleryguard.model.Category;
import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.Metal;
import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;
import com.jeweleryguard.service.CategoryService;
import com.jeweleryguard.service.JewelryService;
import com.jeweleryguard.service.MetalService;
import com.jeweleryguard.service.ShopService;
import com.jeweleryguard.service.UserService;

@Controller
@RequestMapping(value ="/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private JewelryService jewelryService;
	@Autowired
	private MetalService metalService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ShopService shopService;



	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}


	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Istnieje konto z takim adresem email");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Stworzono nowego uzytkownika.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");

		}
		return modelAndView;
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		modelAndView.addObject("userName", "Witaj " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ") + Liczba bizuterii: "+user.getJewelryList().size());
		modelAndView.addObject("adminMessage","Zawartosc widoczna wylacznie dla zalogowanych uzytkownikow");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	@RequestMapping(value="/entities", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam String type){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		

		switch ( type ) {
			case "user":{
				List<User> userList = userService.findAll();
				modelAndView.addObject("userList", userList);
				break;
			}
			case "shop":{
				List<Shop> shopList = shopService.findAll();
				modelAndView.addObject("shopList", shopList);
				break;
			}
			case "category":{
				List<Category> categoryList = categoryService.findAll();
				modelAndView.addObject("categoryList", categoryList);
				break;
			}
			case "metal":{
				
				List<Metal> metalList = metalService.findAll();
				modelAndView.addObject("metalList", metalList);
				break;
			}
			case "jewelry":{
				List<Jewelry> jewelryList = jewelryService.findAll();
				modelAndView.addObject("jewelryList", jewelryList);
				
				break;
			}
		}

		
		
		modelAndView.setViewName("admin/"+type.trim());
		return modelAndView;
	}
		


}
