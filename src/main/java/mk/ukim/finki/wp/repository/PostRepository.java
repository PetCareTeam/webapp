package mk.ukim.finki.wp.repository;


import java.util.List;

import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.model.PetUser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
	
	public List<Post> findByPet(PetUser petUser);
	
	public List<Post> findByType (String type);
	
	public int countByPet(PetUser petUser);
 
}
