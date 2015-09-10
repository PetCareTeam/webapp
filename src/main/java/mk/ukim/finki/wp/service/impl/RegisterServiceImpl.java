package mk.ukim.finki.wp.service.impl;

import java.util.List;

import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.repository.PetUserRepository;
import mk.ukim.finki.wp.service.PetUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements PetUserService {

	@Autowired // injection
	PetUserRepository repository;

	@Override
	public List<PetUser> getUsers() {
		
		List<PetUser> users = repository.findAll();
		return users;
	}

	@Override
	public PetUser findUser(Long id) {
	return repository.findOne(id);
	}

	@Override
	public PetUser register(String username, String password, String firstName, String lastName, String profileImage,int enabled,String skypeId) {
			PetUser newUser = new PetUser();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setProfileImage(profileImage);
			newUser.setEnabled(enabled);
			newUser.setSkypeId(skypeId);
			repository.save(newUser);
			return newUser;
			
		}

	@Override
	public PetUser findByUsername(String username) {
		PetUser user=repository.findByUsername(username);
		return user;
	}
}
