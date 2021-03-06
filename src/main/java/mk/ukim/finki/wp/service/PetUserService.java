package mk.ukim.finki.wp.service;


import java.util.List;

import mk.ukim.finki.wp.model.PetUser;

public interface PetUserService {

	public PetUser register(String username, String password,String firstName,String lastName,String profileImage,int enabled,String skypeId);
	
	public List<PetUser> getUsers();
	
	public PetUser findUser(Long id);
	
	public PetUser findByUsername(String username);
	
	
	
}


