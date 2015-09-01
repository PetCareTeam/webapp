package mk.ukim.finki.wp.controllers;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@Autowired
	PetUserService user_service;

	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Pet Care welcome page");
		model.addObject("message", "We care about animals!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView profilePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user=user_service.findUser(username);
		
		if(user!=null)
		{
			model.addObject("user", user);
		}
		
		
		return model;
	}
	
	/*@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView log(@ModelAttribute("user") User user, BindingResult result){
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("profile");
		
		U user=service.register(petuser.getUsername(), petuser.getPassword(),
				petuser.getFirstName(),petuser.getLastName(),"C:\\Users\\Denicija\\Pictures\\20150724_233039.jpg");
		
		//System.out.println("/////////////////////////////////// " + user.getUsername() + "   " + user.getPassword() + "Picture " +picturePath);
		
		mav.addObject("user",user);
		return mav;
		
	}*/

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