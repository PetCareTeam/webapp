package mk.ukim.finki.wp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.model.Likedpost;
import mk.ukim.finki.wp.model.ListLikes;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.service.LikedPostService;
import mk.ukim.finki.wp.service.PetUserService;
import mk.ukim.finki.wp.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

	@Autowired
	LikedPostService likedpost_service;
	
	@Autowired
	PostService post_service;

	@Autowired
	PetUserService petuser_service;
	
	@RequestMapping(value="/posts/like", method=RequestMethod.POST)
	public Post like(@RequestBody Post post, HttpServletResponse reposnse)
	{
		if(!canLike(post.getId())){
			// SAKA DA NAPRAI UNLIKE
			// TODO reposnse.setStatus(HttpServletResponse.SC_RESET_CONTENT);
			reposnse.setStatus(HttpServletResponse.SC_CONFLICT);
			System.out.println(post.getLikes()+ " od kontroller");
			likedpost_service.deleteLiked(post);
			post_service.deleteLike(post);
			return post;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		likedpost_service.saveLike(post, user);
		post_service.update(post.getId());
		return post;
		
	}
	
	public boolean canLike(Long id){
		
		Post post=post_service.findPost(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		List<Likedpost> list=likedpost_service.findLikedpostByPost(post);
		for (Likedpost likedpost : list) {
			if(likedpost.getLiker().getId() == user.getId())
			{
				return false;
			}
		}
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/posts/liked", method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ListLikes getLikesByUser(HttpServletResponse reposnse)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		List<Likedpost> list=likedpost_service.findByUser(user);
		ListLikes lista=new ListLikes();
		lista.setLikes(list);
		return lista;
		
	}
	
	
	
	
	
	
	/* false znaci deka moze da lajkne, true znaci deka ne moze da lajkne */
	@RequestMapping(value="/posts/getlikes/{id}", method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ListLikes check(@PathVariable("id") Long id, HttpServletResponse reposnse)
	{
		Post post=post_service.findPost(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PetUser user = petuser_service.findByUsername(username);
		List<Likedpost> list=likedpost_service.findLikedpostByPost(post);
		ListLikes lista=new ListLikes();
		boolean result = false;
		if(list.size() != 0){
		boolean liked=false;
		for (Likedpost likedpost : list) {
			if(likedpost.getLiker().getId() == user.getId())
			{
				System.out.println("user-ot vekje lajknal.");
				liked=true;
				break;
			}
		}
		if(liked)
		{
			list.clear();
		}
		lista.setLikes(list);
			if (lista.getLikes().size() > 0) { result = false; } else  { result = true; }
		}
		
		lista.setResult(result);
		System.out.println((result) ? false : true);
		
		return lista;
		
	}
	
	
	
	
}
