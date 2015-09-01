package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.Authorities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
	 
}
