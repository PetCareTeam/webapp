package mk.ukim.finki.wp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

public class PetSocialUser extends SocialUser{

	
	public PetSocialUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	

}
