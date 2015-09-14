package mk.ukim.finki.wp.controllers;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.ListComments;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.service.CommentService;
import mk.ukim.finki.wp.service.PetUserService;
import mk.ukim.finki.wp.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

@RestController
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
	public Comment postComment(@RequestBody Comment comment,
			HttpServletRequest request, HttpServletResponse response) {

	
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		Comment comm=comment_service.postComment(user, comment.getPost(), comment.getMessage(), new Date());
		Post post=comment.getPost();
		post_service.updateReplay(post.getId());
		return comm;

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
			list_comments.setPost(post);
			return list_comments;
		} else
			return null;
	}	
}
