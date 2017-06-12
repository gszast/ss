package com.jeweleryguard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jeweleryguard.model.Shop;
import com.jeweleryguard.service.AttachmentService;
import com.jeweleryguard.service.FileService;
import com.jeweleryguard.service.UserService;

@Controller
@RequestMapping(value = "/attach")
public class AttachmentController {

	@Autowired
	private UserService userService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private FileService fileService;
	

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newJewelry(){
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newShop(@Valid Shop shop, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listShops(){
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}


}
