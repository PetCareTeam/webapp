package mk.ukim.finki.wp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.repository.UserRepository;
import mk.ukim.finki.wp.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired // injection
	UserRepository repository;
	
	@Override
	public PetUser register(String username, String password) {
		
		PetUser newUser = new PetUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		repository.save(newUser);
		System.out.println("MY NEW USER'S USERNAME AND PASSWORD ARE: "  + newUser.getId() + " " + newUser.getUsername() + " " + newUser.getPassword());
		
		return newUser;
		
	}

	@Override
	public List<PetUser> getUsers() {
		
		List<PetUser> users = repository.findAll();
		return users;
	}

}
