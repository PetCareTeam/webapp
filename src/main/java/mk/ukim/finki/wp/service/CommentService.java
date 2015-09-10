package mk.ukim.finki.wp.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;

public interface CommentService {
	
	public Comment post(PetUser username, Date time_post,String message,Double longitude, 
			Double latitude,String type,String image_comment,String contact_phone, String address);
	
	public List<Comment> getComments();
	
	public List<Comment> findByPet(PetUser username);
	
	public List<Comment> findByType(String type);
	
	public int countByPet(PetUser petUser);
}


