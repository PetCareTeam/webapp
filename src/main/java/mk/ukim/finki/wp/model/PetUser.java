package mk.ukim.finki.wp.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="users")
public class PetUser {
	
	@Id
	@Email(message="Invalid email address")
	@Column(name="username",nullable=false,unique=true)
	private String username;
	
	@Size(min=4,max=10,message="password must be beetween 4 and 10 characters")
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="firstName",nullable=false)
	private String firstName;
	
	@Column(name="lastName",nullable=false)
	private String lastName;
	
	@Lob
	@Column(name="profileImage",nullable=true)
	private byte[] profileImage;
	
	@Column(name="enabled", nullable=false)
	private int enabled;
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	
	public PetUser() {
		super();
	}

	public PetUser(String username, String password, String firstName,
			String lastName, byte[] profileImage, int enabled) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileImage = profileImage;
		this.enabled = enabled;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
