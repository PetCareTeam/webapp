package mk.ukim.finki.wp.repository;


import java.util.List;

import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.PetUser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByPet(PetUser petUser);
	
	public List<Comment> findByType (String type);
	
	public int countByPet(PetUser petUser);
 
}
