package com.jewelryguard.controller;

import com.jewelryguard.model.*;
import com.jewelryguard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
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

			modelAndView.setViewName("jewelrys/list");

		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showAll(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false)Integer limit){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(page == null){
			page = 0;
		}
		if(limit == null){
			limit = 10;
		}
		Pageable pageable = createPageRequest(page,limit);
		Page<Jewelry> jewelryList = jewelryService.findAllByUser(user, pageable);


		modelAndView.addObject("jewelryList", jewelryList );
		modelAndView.setViewName("jewelrys/list");
		return modelAndView;
	}

	/*
	 * Single
	 *
	 *
	 */
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
		MyFile myFile = new MyFile();
		myFile.setJewelry(jewelry);
		modelAndView.addObject("jewelry", jewelry);
		modelAndView.addObject("images", images);
		modelAndView.addObject("myFile", myFile);
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
	public ModelAndView deleteOne( @PathVariable("jewelryId") int jewelryId){
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Jewelry jewelry = jewelryService.findOne(jewelryId);
		if ( !jewelry.getUser().getEmail().equals(auth.getName()) ) {
			modelAndView.setViewName("jguard");
			throw new AccessDeniedException("You have no access!");
		}

		boolean isDeleted = jewelryService.delete(jewelry);
		Page<Jewelry> jewelryList = jewelryService.findAllByUser(user, createPageRequest(0,10));

		modelAndView.addObject("message", isDeleted ? "Success: deleted jewelry.":"Error");
		modelAndView.addObject("jewelryList", jewelryList);
		modelAndView.setViewName("jewelrys/list");

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
		modelAndView.addObject( "jewelry", jewelry);
		modelAndView.addObject("images", myFileService.findAll().stream().map(MyFile::getId).collect(Collectors.toList()));

		modelAndView.setViewName("comp/j-img-list");
		return modelAndView;
	}

	private Pageable createPageRequest(Integer page, Integer limit){
		return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
	}
}
