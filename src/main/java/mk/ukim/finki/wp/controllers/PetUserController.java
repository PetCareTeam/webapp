package mk.ukim.finki.wp.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class PetUserController{

	@Autowired
	// injection
	PetUserService service;

	@Autowired
	AuthoritiesService role_service;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		List<PetUser> users = service.getUsers();
		ModelAndView maw = new ModelAndView();
		maw.setViewName("all");
		maw.addObject("users", users);
		return maw;
	}

	@RequestMapping(value = "/done", method = RequestMethod.POST)
	public ModelAndView registerNewPetUser(@Valid @ModelAttribute("petuser") PetUser petuser,
			BindingResult result,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getFieldErrors().toString());
			mav.setViewName("register");
			return mav;
		}

		if (!image.isEmpty()) {
			try {
				
				FilePicture.validateImage(image);

			} catch (RuntimeException re) {
				result.reject(re.getMessage());
				mav.setViewName("register");
				return mav;
			}

			System.out.println(image.getOriginalFilename());
		}
		PetUser user = service.findByUsername(petuser.getUsername());
		if (user != null) {
			mav.setViewName("register");
			mav.addObject("error", "This Username is taken");
			return mav;
		}
		try {
			user = service.register(petuser.getUsername(),
					petuser.getPassword(), petuser.getFirstName(),
					petuser.getLastName(), image.getBytes(), 1);
			
			role_service.save_role(user, "ROLE_USER");
			mav.addObject("title", "Pet Care welcome page");
			mav.addObject("message", "U are seccessfully register, u can now log in!");
			mav.setViewName("hello");
			return mav;
		} catch (IOException io) {
			result.reject(io.getMessage());
			mav.setViewName("register");
			return mav;
		}
	}
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ModelAndView welcomePage(@PathVariable("id") Long id) {

		System.out.println("Username is "+ id);
		ModelAndView model = new ModelAndView();
		model.setViewName("profile");
		PetUser user=service.findUser(id);
		if(user!=null)
		{
			System.out.println("User "+ user.getFirstName());
			model.addObject("user", user);
		}
		return model;
	}
}
