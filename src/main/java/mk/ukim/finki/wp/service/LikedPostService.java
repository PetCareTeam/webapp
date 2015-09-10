package mk.ukim.finki.wp.service;

import java.util.List;

import mk.ukim.finki.wp.model.Likedpost;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;

public interface LikedPostService {
	public void saveLike(Post post, PetUser user);
	public List<Likedpost> findLikedpostByPost(Post post);
}


