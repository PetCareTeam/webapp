package mk.ukim.finki.wp.controllers;


import java.io.IOException;
import java.util.Date;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetUserCommentController {

	@Autowired
	private  PetUserService petuser_service;
	
	@Autowired
	private CommentService comment_service;
	
	
	
	@RequestMapping(value="/post", method = RequestMethod.POST )
	public ModelAndView postComment(@ModelAttribute("comment") Comment comment, BindingResult result, 
			@RequestParam(value = "image", required = false) MultipartFile image){
		ModelAndView mav=new ModelAndView();
		if(result.hasErrors())
		{
			System.out.println("Errorr!");
			mav.setViewName("profile");
			return mav;
		}
		if (!image.isEmpty()) {
			try {
				
				FilePicture.validateImage(image);

			} catch (RuntimeException re) {
				result.reject(re.getMessage());
				mav.setViewName("profile");
				return mav;
			}
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user=petuser_service.findUser(username);
		try {
			Date date=new Date();
		Comment comment2=comment_service.post(user,date,comment.getMessage(), 0.0, 0.0, comment.getType(),image.getBytes(), comment.getContact_phone());
		String base64string=FilePicture.getBytes(comment2.getImage_comment());

		mav.setViewName("comment");
		mav.addObject("comment",comment2);
		mav.addObject("pic",base64string);
		return mav;
		} catch (IOException e) {
			result.reject(e.getMessage());
			mav.setViewName("register");
			return mav;
		}
		
		
	}
	
	
	
}
