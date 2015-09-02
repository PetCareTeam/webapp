package mk.ukim.finki.wp.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;

public interface CommentService {
	
	public Comment post(PetUser username, Date time_post,String message,Double longitude, Double latitude,String type,byte[] image_comment,String contact_phone);
	
	public List<Comment> getComments();
	
	public List<Comment> findByPet(PetUser username);
	
}

