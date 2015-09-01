package mk.ukim.finki.wp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class Authorities extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "username")
	private PetUser username;
	
	@Column(name="rolename")
	private String authority;

	public PetUser getUsername() {
		return username;
	}

	public void setUsername(PetUser username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}