package com.jewelryguard.controller;

import com.jewelryguard.model.*;
import com.jewelryguard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		modelAndView.setViewName("admin");
		return modelAndView;
	}

	@RequestMapping(params = "{add}" ,method = RequestMethod.GET)
	public ModelAndView homeEdit(@RequestParam String add, Model model){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		

		switch ( add ) {
			case "user":{
				List<User> userList = userService.findAll();
				modelAndView.addObject("user", new User());
				modelAndView.addObject("userList", userList);
				break;
			}
			case "shop":{
				List<Shop> shopList = shopService.findAll();
				modelAndView.addObject("shop", new Shop());
				modelAndView.addObject("shopList", shopList);
				break;
			}
			case "category":{
				List<Category> categoryList = categoryService.findAll();
				modelAndView.addObject("category", new Category());
				modelAndView.addObject("categoryList", categoryList);
				break;
			}
			case "metal":{
				
				List<Metal> metalList = metalService.findAll();
				modelAndView.addObject("metal", new Metal());
				modelAndView.addObject("metalList", metalList);
				break;
			}
			case "jewelry":{
				List<Jewelry> jewelryList = jewelryService.findAll();

				modelAndView.addObject("jewelry", new Jewelry());
				modelAndView.addObject("jewelryList", jewelryList);
				
				break;
			}
		}

		
		
		modelAndView.setViewName("admin/"+add.trim());
		return modelAndView;
	}


}
