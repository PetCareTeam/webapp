package mk.ukim.finki.wp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="users")
public class PetUser {
	
	@Id
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="firstName",nullable=false)
	private String firstName;
	
	@Column(name="lastName",nullable=false)
	private String lastName;
	
	@Column(name="profileImage",nullable=true)
	private String profileImage;
	
	@Column(name="enabled", nullable=false)
	private int enabled;
	
	public int getEnabled() {
		return enabled;
	}






	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}






	public PetUser() {
		// TODO Auto-generated constructor stub
	}

	

	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileImage() {
		return profileImage;
	}






	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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
