package mk.ukim.finki.wp.repository;

import java.util.List;

import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.model.PetUser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PetUserRepository extends JpaRepository<PetUser, Long> {
	
	public PetUser findByUsername(String username);
}
