package mk.ukim.finki.wp.service;

import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;

import org.joda.time.DateTime;

public interface CommentService {
	
	public Comment post(PetUser username, DateTime time_post,String message,Double longitude, Double latitude,String type,String image_comment,String contact_phone);
	
	public List<Comment> getComments();
	
	public List<Comment> findByPetUsername(String username);
	
}

