package mk.ukim.finki.wp.controllers;

import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@Autowired
	PetUserService user_service;
	
	@Autowired
	CommentService comment_service;

	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Pet Care welcome page");
		model.addObject("message", "We care about animals!");
		model.setViewName("hello");
		return model;

	}
	

	@RequestMapping(value = "/admin", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public PetUser profilePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("profile");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user=user_service.findByUsername(username);
		if(user!=null)
		{
			model.addObject("user", user);
			String pic=FilePicture.getBytes(user.getProfileImage());
			List<Comment> comments=comment_service.findByPet(user);
			model.addObject("comments",comments);
			model.addObject("pic",pic);
		}
		return user;
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String entry() {
		return "register";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		model.setViewName("login");
		
		return model;
	}
	
	
	

}