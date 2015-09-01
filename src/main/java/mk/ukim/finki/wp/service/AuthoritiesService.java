package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Authorities;
import mk.ukim.finki.wp.model.PetUser;


public interface AuthoritiesService  {

	public Authorities save_role(PetUser username,String role);
}
