package mk.ukim.finki.wp.service.impl;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.repository.CommentRepository;
import mk.ukim.finki.wp.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired // injection
	CommentRepository repository;
	@Override
	public List<Comment> getComments() {
		List<Comment> comments = repository.findAll();
		return comments;
	}
	@Override
	public Comment post(PetUser pet, Date time_post, String message,
			Double longitude, Double latitude, String type,
			byte[] image_comment, String contact_phone) {
		
		Comment comment=new Comment();
		comment.setPet(pet);
		comment.setTime_post(time_post);
		comment.setMessage(message);
		comment.setLongitude(longitude);
		comment.setLatitude(latitude);
		comment.setType(type);
		comment.setImage_comment(image_comment);
		comment.setContact_phone(contact_phone);
		
		repository.save(comment);
		
		return comment;
	}



	@Override
	public List<Comment> findByPet(PetUser username) {
		
		List<Comment> comments = repository.findByPet(username);
		if(comments.isEmpty()) 
			System.out.println("listata e prazna");
		return comments;
	}


			
}
