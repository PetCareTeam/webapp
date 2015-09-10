package mk.ukim.finki.wp.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.model.PetUser;

public interface PostService {
	
	public Post post(PetUser username, Date time_post,String message,Double longitude, 
			Double latitude,String type,String image_comment,String contact_phone, String address,int likes);
	
	public List<Post> getPosts();
	
	public List<Post> findByPet(PetUser username);
	
	public List<Post> findByType(String type);
	
	public int countByPet(PetUser petUser);
	
	public Post findPost(Long id_post);
	
	public void update(Long id_post);
}


