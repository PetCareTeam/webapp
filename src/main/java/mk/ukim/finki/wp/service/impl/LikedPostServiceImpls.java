package mk.ukim.finki.wp.service.impl;

import java.util.List;

import mk.ukim.finki.wp.model.Likedpost;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.repository.LikedPostsRepository;
import mk.ukim.finki.wp.service.LikedPostService;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikedPostServiceImpls  implements LikedPostService{
	
	@Autowired
	LikedPostsRepository repository;
	

	@Autowired
	PetUserService service;

	@Override
	public void saveLike(Post post, PetUser user) {
		
		Likedpost like=new Likedpost();
		like.setLiker(user);
		like.setPost(post);
		repository.save(like);
	}
	@Override 
	public List<Likedpost> findLikedpostByPost(Post post) {		
		List<Likedpost> liked_posts=repository.findByPost(post);
		return liked_posts;
	}
	@Override
	public void deleteLiked(Post post) {
		
		List<Likedpost> lista=findLikedpostByPost(post);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String string = auth.getName();
		PetUser user = service.findByUsername(string);
		for (Likedpost likedpost : lista) {
			if(likedpost.getLiker().getId()==user.getId())
			{
				repository.delete(likedpost.getId());
				break;
			}
		}
		
	}
	@Override
	public List<Likedpost> findByUser(PetUser user) {
		List<Likedpost> lista=repository.findByLiker(user);
		return lista;
	}
}
