package com.jeweleryguard.controller;

import com.jeweleryguard.model.Metal;
import com.jeweleryguard.service.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/metals")
public class MetalController {

	@Autowired
	private MetalService metalService;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveMetal(@Valid Metal metal, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		metalService.save(metal);
		List<Metal> metalList = metalService.findAll();
		modelAndView.addObject("metalList",metalList);
		modelAndView.setViewName("admin/metal");
		modelAndView.addObject("successMessage", "Dodano do nowy kruszec.");
		return modelAndView;
	}

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listMetals(){
		ModelAndView modelAndView = new ModelAndView();
		List<Metal> metalList = metalService.findAll();
		modelAndView.addObject("metal", new Metal());
		modelAndView.addObject("metalList", metalList);
		modelAndView.setViewName("admin/metal");
		return modelAndView;
	}


}
