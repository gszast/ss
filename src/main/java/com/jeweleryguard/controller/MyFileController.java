package com.jeweleryguard.controller;

import com.jeweleryguard.model.MyFile;
import com.jeweleryguard.service.MyFileService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/j")
public class MyFileController {

	@Autowired
	private MyFileService myFileService;

	@Autowired
	private JewelerService

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveJewelleryFiles(@Valid MyFile myFile, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		myFileService.save(myFile);
		List<MyFile> myFileList = myFileService.findAll();
		modelAndView.addObject("myFileList",myFileList);
		modelAndView.setViewName("admin/myFile");
		modelAndView.addObject("successMessage", "Dodano do nowy kruszec.");
		return modelAndView;
	}

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listJewelleryFiles(){
		ModelAndView modelAndView = new ModelAndView();
		List<MyFile> myFileList = myFileService.findAll();
		modelAndView.addObject("myFile", new MyFile());
		modelAndView.addObject("myFileList", myFileList);
		modelAndView.setViewName("admin/myFile");
		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView deleteJewelleryFiles(@RequestBody String b64File{
		ModelAndView modelAndView = new ModelAndView();
		jewelerySer
		myFileService.write(b64File, jewellery_id+".jpg");
		return modelAndView;
	}

}
