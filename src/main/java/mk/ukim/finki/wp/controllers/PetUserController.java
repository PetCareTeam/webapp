package mk.ukim.finki.wp.controllers;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.PictureUpload;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.impl.RegisterServiceImpl;
import mk.ukim.finki.wp.validator.FileValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/users")
public class PetUserController {

	@Autowired // injection
	RegisterServiceImpl service;
	
	@Autowired
	FileValidator validator;
	
	@Autowired
	AuthoritiesService role_service;
	
	public RegisterServiceImpl getService() {
	
		return service;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String entry(){
		/*PictureUpload picture = new PictureUpload();
		model.addAttribute("picture", picture);*/
		return "register";
	}
	

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		PictureUpload picture = new PictureUpload();
		model.addAttribute("file", picture);
		return "register";
	}

	@RequestMapping(value="/image", method = RequestMethod.POST)
	public ModelAndView fileUploaded(Model model, @Validated PictureUpload picture,
			BindingResult result) {
		String returnVal="";
		System.out.println("Denicija");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("profile");
		
		if (result.hasErrors()) {
			mav.setViewName("register");
		} else {			
			
			MultipartFile multipartFile = picture.getFile();
			mav.addObject("picture",multipartFile.getOriginalFilename());
		}
		return mav;
	}
	
	@RequestMapping(value="/done", method = RequestMethod.POST)
	public ModelAndView log(@ModelAttribute("petuser") PetUser petuser,BindingResult result) {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("profile");
		
		/*if(petuser.getProfileImage()!=null)
		System.out.println("File:" + petuser.getProfileImage().length);*/
		/*.out.println("ContentType:" + file.getContentType());*/
		
		PetUser user=service.register(petuser.getUsername(), petuser.getPassword(),
				petuser.getFirstName(),petuser.getLastName(),petuser.getProfileImage(),1);
		
		Authorities role=role_service.save_role(user, "ROLE_USER");
		if(role==null) System.out.println("____________________Null");
		//System.out.println("/////////////////////////////////// " + user.getUsername() + "   " + user.getPassword() + "Picture " +picturePath);
		
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
