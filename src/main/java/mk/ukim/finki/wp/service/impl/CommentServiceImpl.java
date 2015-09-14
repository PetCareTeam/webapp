package mk.ukim.finki.wp.service.impl;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.repository.CommentRepository;
import mk.ukim.finki.wp.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	CommentRepository repository;
	@Override
	public Comment postComment(PetUser petUser, Post post, String message,
			Date time_comment) {
		
		Comment comment=new Comment();
		comment.setUser(petUser);
		comment.setPost(post);
		comment.setMessage(message);
		comment.setTime_post(time_comment);
		
		repository.save(comment);
		return comment;
	}

	@Override
	public List<Comment> findByPost(Post post) {
		List<Comment> comments=repository.findByPost(post);
		return comments;
	}

}
