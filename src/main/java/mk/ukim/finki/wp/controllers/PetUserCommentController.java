package mk.ukim.finki.wp.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.ListComments;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class PetUserCommentController implements ServletContextAware {

	@Autowired
	private PetUserService petuser_service;

	@Autowired
	private CommentService comment_service;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/comments/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListComments getAllComments() {
		List<Comment> comments = comment_service.getComments();
		ListComments list_comments = new ListComments();
		list_comments.setComments(comments);
		/*
		 * ModelAndView maw = new ModelAndView(); maw.setViewName("all");
		 * maw.addObject("comments", comments);
		 */
		return list_comments;
	}

	@RequestMapping(value = "/comments/post", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Comment postComment(MultipartHttpServletRequest mRequest,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("Vlagam tuka");
		MultipartFile mFile = null;
		if (mFile == null)
			System.out.println("Slikata e null");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		Date date = new Date();
		System.out.println("pred try");
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
		System.out.println("pred vtor try");
		try {
			if (mFile != null && user != null)
				FilePicture.saveImage(
						new String(Base64.encodeBase64(user.getUsername()
								.getBytes())) + date.getTime() + ".jpg", mFile,
						servletContext, "comments");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		System.out.println("pred kreiranje na vtor komentar");	
		System.out.println("comment is: " + mRequest.getParameter("message"));	
		
		Comment	comment =null;
		if(mFile!=null){
		comment = comment_service.post(
					user,
					date,
					mRequest.getParameter("message"),
					Double.parseDouble(mRequest.getParameter("longitude")),
					Double.parseDouble(mRequest.getParameter("latitude")),
					mRequest.getParameter("type"),
					new String(Base64.encodeBase64(user.getUsername()
							.getBytes())) + date.getTime() + ".jpg",
					mRequest.getParameter("contact_phone"),
					mRequest.getParameter("address"));
		}
	else
		comment = comment_service.post(
				user,
				date,
				mRequest.getParameter("message"),
				Double.parseDouble(mRequest.getParameter("longitude")),
				Double.parseDouble(mRequest.getParameter("latitude")),
				mRequest.getParameter("type"),
				null,
				mRequest.getParameter("contact_phone"),
				mRequest.getParameter("address"));

		response.setStatus(HttpServletResponse.SC_OK);

		return comment;

	}

	@RequestMapping(value = "/comments/type/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListComments getCommentsByType(@RequestBody String type,
			HttpServletResponse response) {
		List<Comment> comments = comment_service.findByType(type);
		ListComments list_comments = new ListComments();
		list_comments.setComments(comments);
		return list_comments;
	}

	@RequestMapping(value = "/comments/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListComments getCommentsForUser(@PathVariable("id") long userId,
			HttpServletResponse response, HttpServletRequest request) {

		System.out.println("lalal");
		int pictureNo = 0;
		int locationNo = 0;

		PetUser user = petuser_service.findUser(userId);
		if (user != null) {
			List<Comment> comments = comment_service.findByPet(user);
			for (Comment comment : comments) {
				{
					if (comment.getImage_comment() != null)
						pictureNo++;
					if (comment.getAddress() != null)
						locationNo++;

				}
			}

			ListComments list_comments = new ListComments();
			list_comments.setComments(comments);
			list_comments.setLocationNo(locationNo);
			list_comments.setPictureNo(pictureNo);
			return list_comments;
		} else
			return null;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

}