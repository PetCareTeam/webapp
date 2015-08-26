package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.PetUser;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Нема потреба да креирате имплементации за овие интерфејси, затоа што Spring Data ќе го стори тоа за вас.
 *
 * 
 * */

public interface UserRepository extends JpaRepository<PetUser, Long> {
 
}
