package mk.ukim.finki.wp.service;


import java.util.List;

import mk.ukim.finki.wp.model.PetUser;

public interface RegisterService {

	
	public PetUser register(String username, String password);
	public List<PetUser> getUsers();
	
}


