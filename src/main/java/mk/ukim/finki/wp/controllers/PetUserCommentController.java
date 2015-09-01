package mk.ukim.finki.wp.controllers;


import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetUserCommentController {

	@Autowired
	private  PetUserService petuser_service;
	
	@Autowired
	private CommentService comment_service;
	
	
	
	@RequestMapping(value="/users/post", method = RequestMethod.POST )
	public ModelAndView postComment(@ModelAttribute("comment") Comment comment, BindingResult result){
		if(result.hasErrors())
		{
			return null;
		}
		ModelAndView mav=new ModelAndView();
		mav.setViewName("comment");
		mav.addObject("comment",comment);
		return mav;
		
	}
	
	
	
}
