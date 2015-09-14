package mk.ukim.finki.wp.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;

public interface CommentService {
	
	public Comment postComment(PetUser petUser, Post post, String message, Date time_post);
	
	public List<Comment> findByPost(Post post);

	
}


