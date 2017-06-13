package com.jewelryguard.controller;

import com.jewelryguard.model.Shop;
import com.jewelryguard.model.User;
import com.jewelryguard.service.JewelryService;
import com.jewelryguard.service.ShopService;
import com.jewelryguard.service.UserService;
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
@RequestMapping(value = "/shops")
public class ShopController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private JewelryService jewelryService;
	

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newShop(){
		ModelAndView modelAndView = new ModelAndView();
		Shop shop = new Shop();
		modelAndView.addObject("shop", shop);
		modelAndView.setViewName("shops/new");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveShop(@Valid Shop shop, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//TO DO: check if user has rights to add new shop
		shop.setOwner(user);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("shops/new");
		} else {
			shopService.saveShop(shop);
			List<Shop> shopList = shopService.findAll();
			modelAndView.addObject("successMessage", "Dodano do nowy sklep.");
			modelAndView.addObject("shopList", shopList);
			modelAndView.setViewName("shops");

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
		modelAndView.setViewName("shops");
		return modelAndView;
	}


}
