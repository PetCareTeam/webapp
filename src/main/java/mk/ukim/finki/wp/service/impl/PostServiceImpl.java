package mk.ukim.finki.wp.service.impl;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.repository.PostRepository;
import mk.ukim.finki.wp.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Autowired // injection
	PostRepository repository;
	@Override
	public List<Post> getPosts() {
		List<Post> posts = repository.findAll();
		return posts;
	}
	@Override
	public Post post(PetUser pet, Date time_post, String message,
			Double longitude, Double latitude, String type,
			String image_comment, String contact_phone, String address, int likes) {
		
		Post comment=new Post();
		comment.setPet(pet);
		comment.setTime_post(time_post);
		comment.setMessage(message);
		comment.setLongitude(longitude);
		comment.setLatitude(latitude);
		comment.setType(type);
		comment.setImage_comment(image_comment);
		comment.setContact_phone(contact_phone);
		comment.setAddress(address);
		comment.setLikes(likes);
		repository.save(comment);
		
		
		return comment;
	}
	
	

	@Override
	public List<Post> findByPet(PetUser username) {
		
		List<Post> comments = repository.findByPet(username);
		if(comments.isEmpty()) 
			System.out.println("listata e prazna");
		return comments;
	}
	@Override
	public List<Post> findByType(String type) {
		List<Post> comments=repository.findByType(type);
		return comments;
	}
	@Override
	public int countByPet(PetUser petUser) {
		int number=repository.countByPet(petUser);
		return number;
	}
	@Override
	public Post findPost(Long id_post) {
		Post post=repository.findOne(id_post);
		return post;
	}
	@Override
	public void update(Long id_post) {
		
		Post post=repository.findOne(id_post);
		post.setLikes(post.getLikes()+1);
		//System.out.println(id_post);
		repository.saveAndFlush(post);
		
	}
	@Override
	public void deleteLike(Post post) {
		System.out.println("Likes pred: "+ post.getLikes());
		post.setLikes(post.getLikes()-1);
		System.out.println("Likes po : "+ post.getLikes());
		repository.saveAndFlush(post);
		
	}


			
}
