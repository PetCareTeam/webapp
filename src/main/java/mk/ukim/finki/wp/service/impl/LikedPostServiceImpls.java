package mk.ukim.finki.wp.service.impl;

import java.util.List;

import mk.ukim.finki.wp.model.Likedpost;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.repository.LikedPostsRepository;
import mk.ukim.finki.wp.service.LikedPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikedPostServiceImpls  implements LikedPostService{
	
	@Autowired
	LikedPostsRepository repository;

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
}
