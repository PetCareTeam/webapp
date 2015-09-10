package mk.ukim.finki.wp.controllers;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.resources.TokenTransfer;
import mk.ukim.finki.wp.resources.TokenUtils;
import mk.ukim.finki.wp.service.AuthoritiesService;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController implements ServletContextAware {

	@Autowired
	PetUserService user_service;

	@Autowired
	AuthoritiesService role_service;

	@Autowired
	CommentService comment_service;

	@Autowired
	UserDetailsService user_details_setvice;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Pet Care welcome page");
		model.addObject("message", "We care about animals!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/userDetails/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public PetUser profilePage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") Long id) {

		System.out.println("Status success: ");
		PetUser user = user_service.findUser(id);

		if (user == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			System.out.println(response.getStatus());
		}
		System.out.println("Status success: " + response.getStatus());
		/*
		 * if(user!=null) { String
		 * pic=FilePicture.getBytes(user.getProfileImage()); List<Comment>
		 * comments=comment_service.findByPet(user);
		 * 
		 * }
		 */
		return user;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView entry() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public PetUser getAuthentication(HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String string = auth.getName();
		PetUser user = user_service.findByUsername(string);
		if(user == null){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return user;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public TokenTransfer auth(@RequestBody User user,
			HttpServletRequest request, HttpServletResponse response) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		System.out.println(authenticationToken);

		try {
			Authentication authentication = this.authenticationManager
					.authenticate(authenticationToken);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			UserDetails userDetails = this.user_details_setvice
					.loadUserByUsername(user.getUsername());

			PetUser petuser = user_service.findByUsername(user.getUsername());

			System.out.println(userDetails.getUsername() + " "
					+ userDetails.getPassword());

			TokenTransfer tokenTransfer = new TokenTransfer(
					TokenUtils.createToken(userDetails), petuser.getId());

			return tokenTransfer;
		} catch (BadCredentialsException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println(e.getMessage());
			return null;
		}

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */

	}

	/*
	 * @RequestMapping(value = "/done", method = RequestMethod.POST) public
	 * ModelAndView registerNewPetUser(@Valid @ModelAttribute("petuser") PetUser
	 * petuser, BindingResult result,
	 * 
	 * @RequestParam(value = "image", required = false) MultipartFile image) {
	 * 
	 * ModelAndView mav = new ModelAndView(); if (result.hasErrors()) {
	 * System.out.println(result.getErrorCount());
	 * System.out.println(result.getFieldErrors().toString());
	 * mav.setViewName("register"); return mav; }
	 * 
	 * if (!image.isEmpty()) { try {
	 * 
	 * FilePicture.validateImage(image);
	 * 
	 * } catch (RuntimeException re) { result.reject(re.getMessage());
	 * mav.setViewName("register"); return mav; }
	 * 
	 * System.out.println(image.getOriginalFilename()); } try {
	 * 
	 * FilePicture.saveImage(new
	 * String(Base64.encodeBase64(petuser.getUsername().getBytes())) + ".jpg",
	 * image,servletContext,"users"); } catch (IOException e) {
	 * System.out.println("slikata nemoze da se zachuva! " + e.getMessage());
	 * result.reject(e.getMessage()); mav.setViewName("profile"); return mav; }
	 * 
	 * 
	 * PetUser user = user_service.findByUsername(petuser.getUsername()); if
	 * (user != null) { mav.setViewName("register"); mav.addObject("error",
	 * "This Username is taken"); return mav; }
	 * 
	 * user = user_service.register(petuser.getUsername(),
	 * petuser.getPassword(), petuser.getFirstName(), petuser.getLastName(), new
	 * String(Base64.encodeBase64(petuser.getUsername().getBytes())) + ".jpg",
	 * 1);
	 * 
	 * role_service.save_role(user, "ROLE_USER"); mav.addObject("title",
	 * "Pet Care welcome page"); mav.addObject("message",
	 * "U are seccessfully register, u can now log in!");
	 * mav.setViewName("hello"); return mav;
	 * 
	 * }
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public PetUser add(MultipartHttpServletRequest mRequest,
			HttpServletRequest request, HttpServletResponse response) {
		MultipartFile mFile = null;

		try {
			mRequest = (MultipartHttpServletRequest) request;
			mRequest.getParameterMap();

			Iterator<String> itr = mRequest.getFileNames();
			while (itr.hasNext()) {
				mFile = mRequest.getFile(itr.next());
				String fileName = mFile.getOriginalFilename();
				System.out.println(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (mFile != null)
				FilePicture.saveImage(
						new String(Base64.encodeBase64(mRequest.getParameter(
								"username").getBytes()))
								+ ".jpg", mFile, servletContext, "users");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		PetUser pet_user = user_service.findByUsername(mRequest
				.getParameter("username"));
		if (pet_user != null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
		
			System.out.println("Userot ne e null");
			System.out.println("Username " + mRequest.getParameter("username"));
			System.out.println("PAssword " + mRequest.getParameter("password"));
			System.out.println("fisrtName " + mRequest.getParameter("firstName"));
			System.out.println("LastName " + mRequest.getParameter("lastName"));
			System.out.println("SkypeId " + mRequest.getParameter("skypeId"));
				pet_user = user_service.register(
						mRequest.getParameter("username"),
						mRequest.getParameter("password"),
						mRequest.getParameter("firstName"),
						mRequest.getParameter("lastName"),
						new String(Base64.encodeBase64(mRequest.getParameter(
								"username").getBytes()))
								+ ".jpg", 1,
						mRequest.getParameter("skypeId"));
				
				role_service.save_role(pet_user, "ROLE_USER");
		}
		return null;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			System.out.println("imam error");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		model.setViewName("login");

		return model;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

}