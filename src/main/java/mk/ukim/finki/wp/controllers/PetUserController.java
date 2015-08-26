package mk.ukim.finki.wp.controllers;



import java.util.List;

import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.impl.RegisterServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users")
public class PetUserController {

	@Autowired // injection
	RegisterServiceImpl service;
	
	public RegisterServiceImpl getService() {
	
		return service;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String entry(){
		return "register";
	}
	
	@RequestMapping(value="/done", method = RequestMethod.POST)
	public String log(@RequestParam("username") String username, @RequestParam("password") String pass){
		System.out.println("/////////////////////////////////// " + username + "   " + pass);
		service.register(username, pass);
		return "errorpage";
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
