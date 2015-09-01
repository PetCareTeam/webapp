package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.repository.AuthoritiesRepository;
import mk.ukim.finki.wp.service.AuthoritiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	@Autowired // injection
	AuthoritiesRepository repository;

	@Override
	public Authorities save_role(PetUser username, String role) {
		Authorities auth=new Authorities();
		auth.setAuthority(role);
		auth.setUsername(username);
		repository.save(auth);
		return auth;
	}
	
	

}
