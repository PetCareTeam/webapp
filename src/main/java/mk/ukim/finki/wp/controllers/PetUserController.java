package mk.ukim.finki.wp.controllers;
import java.util.List;

import javax.validation.Valid;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.impl.RegisterServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/users")
public class PetUserController {

	@Autowired // injection
	RegisterServiceImpl service;
	
	@Autowired
	AuthoritiesService role_service;
	
	public RegisterServiceImpl getService() {
	
		return service;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String entry(){
		return "register";
	}
	
	
	@RequestMapping(value="/done", method = RequestMethod.POST)
	public ModelAndView log(@Valid @ModelAttribute("petuser") PetUser petuser,BindingResult result) {
		
		ModelAndView mav=new ModelAndView();
		if(result.hasErrors())
		{
			System.out.println(result.getErrorCount());
			System.out.println(result.getFieldErrors().toString());
			mav.setViewName("register");
			return mav;
		}
		
		mav.setViewName("profile");
		PetUser user=service.findUser(petuser.getUsername());
		if(user!=null)
		{
			mav.setViewName("register");
			mav.addObject("error", "This Username is taken");
			return mav;
		}
		
		user=service.register(petuser.getUsername(), petuser.getPassword(),
				petuser.getFirstName(),petuser.getLastName(),petuser.getProfileImage(),1);
		Authorities role=role_service.save_role(user, "ROLE_USER");
		
		mav.addObject("user",user);
		return mav;
		
	}

	
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ModelAndView getEm(){
		List<PetUser> users = service.getUsers();
		ModelAndView maw = new ModelAndView();
		maw.setViewName("all");
		maw.addObject("users",users);
		return maw;
	}
}
