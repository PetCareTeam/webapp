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
	@JoinColumn(name = "pet",unique=true)
	private PetUser pet;
	
	@Column(name="rolename")
	private String authority;

	public PetUser getPet() {
		return pet;
	}

	public void setPet(PetUser pet) {
		this.pet = pet;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}