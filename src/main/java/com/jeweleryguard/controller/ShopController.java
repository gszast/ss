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

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;
import com.jeweleryguard.service.JewelryService;
import com.jeweleryguard.service.ShopService;
import com.jeweleryguard.service.UserService;

@Controller
@RequestMapping(value = "/shops")
public class ShopController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private JewelryService jewelryService;
	

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newJewelry(){
		ModelAndView modelAndView = new ModelAndView();
		Shop shop = new Shop();
		modelAndView.addObject("shop", shop);
		modelAndView.setViewName("shops/new");
		return modelAndView;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newShop(@Valid Shop shop, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//TO DO: check if user has rights to add new shop
		shop.setOwner(user);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("shops/new");
		} else {
			shopService.saveShop(shop);
			modelAndView.addObject("successMessage", "Dodano do nowy sklep.");
			modelAndView.addObject("shop", shop);
			modelAndView.setViewName("shops/index");

		}
		return modelAndView;
	}

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listShops(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//TO DO: show shops only to related users
		List<Shop> shopList = shopService.findAll();
		modelAndView.addObject("shopList", shopList );
		modelAndView.setViewName("shops/list");
		return modelAndView;
	}


}
