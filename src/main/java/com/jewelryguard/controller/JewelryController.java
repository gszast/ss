package com.jewelryguard.controller;

import com.jewelryguard.model.*;
import com.jewelryguard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
	public ModelAndView create(){
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

	/*
	 * Multiple
	 *
	 */

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveOne(@Valid Jewelry jewelry, BindingResult bindingResult) {
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
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showAll(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Jewelry> jewelryList = user.getJewelryList();
		modelAndView.addObject("jewelryList", jewelryList );
		modelAndView.setViewName("jewelrys");
		return modelAndView;
	}

	/*
	 * Single
	 *
	 *
	 */

	@RequestMapping(value="/{jewelryId}", method = RequestMethod.GET)
	public ModelAndView showOne(@PathVariable("jewelryId") int jewelryId){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Jewelry jewelry = jewelryService.findOne(jewelryId);

		List<Integer> images = myFileService.findAllByJewelry(jewelry).
				stream().
				map(MyFile::getId).
				collect(Collectors.toList());

		modelAndView.addObject("jewelry", jewelry);
		modelAndView.addObject("images", images);
		modelAndView.setViewName("jewelrys/index");
		return modelAndView;
	}
	@RequestMapping(value="/{jewelryId}", method = RequestMethod.PUT)
	public ModelAndView updateOne(@PathVariable("jewelryId") int jewelryId){
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Jewelry jewelry = jewelryService.findOne(jewelryId);

		modelAndView.addObject("jewelry", jewelry);
		modelAndView.addObject("message", "Succesfully updated jewelry.");
		modelAndView.setViewName("jewelrys/index");
		return modelAndView;
	}

	@RequestMapping(value="/{jewelryId}", method = RequestMethod.DELETE)
	public ModelAndView deleteOne(@PathVariable("jewelryId") int jewelryId){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Jewelry jewelry = jewelryService.findOne(jewelryId);
		boolean isDeleted = jewelryService.delete(jewelry);
		List<Jewelry> jewelryList = jewelryService.findAllByUser(user);
		modelAndView.addObject("message", isDeleted ? "Success: deleted jewelry.":"Error");
		modelAndView.addObject("jewelryList", jewelryList);
		modelAndView.setViewName("jewelrys");
		return modelAndView;
	}





	/*
	 * Images
	 *
	 */

	@RequestMapping(value="/{jewelryId}/images", method = RequestMethod.POST)
	public ModelAndView createOneImage(@PathVariable("jewelryId") int jewelryId, @RequestParam MultipartFile image){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Jewelry jewelry = jewelryService.findOne(jewelryId);
		try {
			myFileService.saveJewelryImage(image, jewelry);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		HashMap<String, String> images = myFileService.findAllByJewelry(jewelry);
		modelAndView.addObject("images", myFileService.findAll().stream().map(MyFile::getPath).collect(Collectors.toList()));
		modelAndView.addObject("myFile", new MyFile() );
		modelAndView.setViewName("jewelrys/index");
		return modelAndView;
	}

	@RequestMapping(value="{jewelryId}/images", method = RequestMethod.GET)
	public ModelAndView showImages(@PathVariable("jewelryId") int jewelryId) {
		ModelAndView modelAndView = new ModelAndView();
		Jewelry jewelry = jewelryService.findOne(jewelryId);
		if (jewelry == null) {
			throw new EntityNotFoundException("Not found jewewlry for id: " + jewelryId);
		}

//		HashMap<String, String> images = myFileService.findAllByJewelry(jewelry);
		modelAndView.addObject("images", myFileService.findAll().stream().map(MyFile::getId).collect(Collectors.toList()));
		modelAndView.addObject("myFile", new MyFile());
		modelAndView.setViewName("jewelrys/images");
		return modelAndView;
	}


}
