package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.PetUser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PetUserRepository extends JpaRepository<PetUser, String> {
 
}
