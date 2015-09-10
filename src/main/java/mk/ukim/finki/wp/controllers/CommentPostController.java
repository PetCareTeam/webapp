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
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;
import mk.ukim.finki.wp.service.PostService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/post")
public class CommentPostController implements ServletContextAware{

	@Autowired
	// injection
	PostService post_service;
	
	@Autowired
	CommentService comment_service;
	
	@Autowired
	PetUserService petuser_service;
	
	@Autowired
	private ServletContext servletContext;
	

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "application/json")
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
		Long idPost=Long.parseLong(mRequest.getParameter("id_post"));
		Post post=post_service.findPost(idPost);
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
		
		Comment comment =null;
		if(mFile!=null){
			String image_name=new String(Base64.encodeBase64(user.getUsername().getBytes())) + date.getTime() + ".jpg";
		comment=comment_service.postComment(user, post, mRequest.getParameter("message"), date,image_name);
				
		}
		else
		comment = comment_service.postComment(user,post,mRequest.getParameter("message"), date,null);

		response.setStatus(HttpServletResponse.SC_OK);

		return comment;

	}
	
	@RequestMapping(value = "/comments/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListComments getCommentsForPost(@PathVariable("id") long postId,
			HttpServletResponse response, HttpServletRequest request) {

		Post post =post_service.findPost(postId);
		if (post != null) {
			List<Comment> comments = comment_service.findByPost(post);
			ListComments list_comments = new ListComments();
			list_comments.setComment(comments);
			return list_comments;
		} else
			return null;
	}
	
	

	
	
}
