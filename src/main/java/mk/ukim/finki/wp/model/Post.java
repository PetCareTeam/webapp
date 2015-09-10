package mk.ukim.finki.wp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

@Entity
@Table(name="posts")
public class Post extends BaseEntity {
	
	@ManyToOne
	private PetUser pet;
	
	@Column(name="message", nullable=false)
	public String message;
	
	@Column(name="address")
	public String address;
	
	@Column(name="time_post")
	@Temporal(TemporalType.TIMESTAMP)
	public Date time_post;
	
	@Column(name="longitude")
	public double longitude;

	@Column(name="latitude")
	public double latitude;
	
	@Column(name="type")
	public String type;

	@Column(name="image_comment")
	public String image_comment;
	
	@Column(name="contact_phone")
	public String contact_phone;
	
	@Column(name="likes")
	public int likes;

	public Post() {
		super();
	}
	
	public Post(PetUser username, String message, Date time_post,
			double longitude, double latitude, String type,
			String image_comment, String contact_phone, String address,int likes) {
		super();
		this.pet = username;
		this.message = message;
		this.time_post = time_post;
		this.longitude = longitude;
		this.latitude = latitude;
		this.type = type;
		this.image_comment = image_comment;
		this.contact_phone = contact_phone;
		this.address=address;
		this.likes=likes;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public PetUser getPet() {
		return pet;
	}
	public void setPet(PetUser pet) {
		this.pet = pet;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Date getTime_post() {
		return time_post;
	}
	public void setTime_post(Date time_post) {
		this.time_post = time_post;
	}
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getImage_comment() {
		return image_comment;
	}
	public void setImage_comment(String image_comment) {
		this.image_comment = image_comment;
	}
	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	
	
	
}
