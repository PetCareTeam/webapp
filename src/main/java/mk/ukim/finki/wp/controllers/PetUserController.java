package mk.ukim.finki.wp.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.PetUserService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class PetUserController implements ServletContextAware{

	@Autowired
	// injection
	PetUserService service;

	@Autowired
	AuthoritiesService role_service;

	private ServletContext servletContext;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String entry() {
		return "register";
	}

	@RequestMapping(value = "/done", method = RequestMethod.POST)
	public ModelAndView log(@Valid @ModelAttribute("petuser") PetUser petuser,
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
				validateImage(image);

			} catch (RuntimeException re) {
				result.reject(re.getMessage());
				mav.setViewName("register");
				return mav;
			}

			System.out.println(image.getOriginalFilename());
		}
		mav.setViewName("profile");
		PetUser user = service.findUser(petuser.getUsername());
		if (user != null) {
			mav.setViewName("register");
			mav.addObject("error", "This Username is taken");
			return mav;
		}
		try {
			user = service.register(petuser.getUsername(),
					petuser.getPassword(), petuser.getFirstName(),
					petuser.getLastName(), image.getBytes(), 1);
			Authorities role = role_service.save_role(user, "ROLE_USER");
			File file = new File(servletContext.getRealPath("/") + "/"
					+ user.getUsername()+".jpg");
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			mav.addObject("file",file.getAbsolutePath());
			mav.addObject("user", user);

			return mav;
		} catch (IOException io) {
			result.reject(io.getMessage());
			mav.setViewName("register");
			return mav;
		}
	}

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("Only JPG images are accepted");
		}
	}
	private void saveImage(String filename, MultipartFile image)
			throws RuntimeException, IOException {
		
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView getEm() {
		List<PetUser> users = service.getUsers();
		ModelAndView maw = new ModelAndView();
		maw.setViewName("all");
		maw.addObject("users", users);
		return maw;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
}
