package mk.ukim.finki.wp.controllers;

import java.util.List;

import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
