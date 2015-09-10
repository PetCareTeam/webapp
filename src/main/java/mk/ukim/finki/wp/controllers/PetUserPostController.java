package mk.ukim.finki.wp.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.model.FilePicture;
import mk.ukim.finki.wp.model.ListPosts;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.service.LikedPostService;
import mk.ukim.finki.wp.service.PetUserService;
import mk.ukim.finki.wp.service.PostService;

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
public class PetUserPostController implements ServletContextAware {

	@Autowired
	private PetUserService petuser_service;

	@Autowired
	private PostService post_service;
	
	@Autowired
	private LikedPostService likedPost_service;

	@Autowired
	private ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/posts/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListPosts getAllComments() {
		List<Post> posts = post_service.getPosts();
		ListPosts list_comments = new ListPosts();
		list_comments.setPosts(posts);
		/*
		 * ModelAndView maw = new ModelAndView(); maw.setViewName("all");
		 * maw.addObject("comments", comments);
		 */
		return list_comments;
	}

	
	@RequestMapping(value="/posts/like", method=RequestMethod.POST)
	@ResponseBody
	public void like(@RequestBody Long post_id, @RequestBody Long user_id)
	{
		Post post=post_service.findPost(post_id);
		PetUser user=petuser_service.findUser(user_id);
		
		System.out.println("User_id " + user_id + "Post_id: "+ post_id);
		likedPost_service.saveLike(post, user);
		
	}
	
	@RequestMapping(value = "/posts/post", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Post postPost(MultipartHttpServletRequest mRequest,
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
						servletContext, "posts");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		System.out.println("pred kreiranje na vtor komentar");	
		System.out.println("comment is: " + mRequest.getParameter("message"));	
		if(mRequest.getParameter("address")==null)
		{
			System.out.println("Addresata e null");
		}
		Post comment =null;
		
		if(mFile!=null ){
		comment = post_service.post(
					user,
					date,
					mRequest.getParameter("message"),
					Double.parseDouble(mRequest.getParameter("longitude")),
					Double.parseDouble(mRequest.getParameter("latitude")),
					mRequest.getParameter("type"),
					new String(Base64.encodeBase64(user.getUsername()
							.getBytes())) + date.getTime() + ".jpg",
					mRequest.getParameter("contact_phone"),
					mRequest.getParameter("address"),0);
		}
		else
		comment = post_service.post(
				user,
				date,
				mRequest.getParameter("message"),
				Double.parseDouble(mRequest.getParameter("longitude")),
				Double.parseDouble(mRequest.getParameter("latitude")),
				mRequest.getParameter("type"),
				null,
				mRequest.getParameter("contact_phone"),
				mRequest.getParameter("address"),0);

		response.setStatus(HttpServletResponse.SC_OK);

		return comment;

	}

	@RequestMapping(value = "/posts/type/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListPosts getCommentsByType(@RequestBody String type,
			HttpServletResponse response) {
		List<Post> comments = post_service.findByType(type);
		ListPosts list_comments = new ListPosts();
		list_comments.setPosts(comments);
		return list_comments;
	}

	@RequestMapping(value = "/posts/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ListPosts getCommentsForUser(@PathVariable("id") long userId,
			HttpServletResponse response, HttpServletRequest request) {

		System.out.println("lalal");
		int pictureNo = 0;
		int locationNo = 0;

		PetUser user = petuser_service.findUser(userId);
		if (user != null) {
			List<Post> posts = post_service.findByPet(user);
			for (Post post : posts) {
				{
					if (post.getImage_comment() != null)
						pictureNo++;
					if (post.getAddress() != null)
					{
						if(!post.getAddress().equals("")){
						System.out.println("-"+post.getAddress() +"-");
						locationNo++;	
						}
					}

				}
			}

			ListPosts list_comments = new ListPosts();
			
			list_comments.setPosts(posts);
			list_comments.setLocationNo(locationNo);
			list_comments.setPictureNo(pictureNo);
			System.out.println(posts.get(0).message);
			return list_comments;
		} else
			return null;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

}