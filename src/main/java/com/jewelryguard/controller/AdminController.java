package com.jewelryguard.controller;

import com.jewelryguard.model.*;
import com.jewelryguard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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





	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView homeEdit(
			@RequestParam(value = "e", required = true) String e, Model model){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		

		switch ( e ) {
			case "user":{
				List<User> userList = userService.findAll();
				modelAndView.addObject("roles", user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));
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

		
		
		modelAndView.setViewName("admin/"+e.trim());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		modelAndView.setViewName("comp/nav-a");
		return modelAndView;
	}

}
