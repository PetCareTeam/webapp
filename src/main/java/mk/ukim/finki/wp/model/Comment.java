package mk.ukim.finki.wp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="comments")
public class Comment extends BaseEntity {
	
	
	@ManyToOne
	private PetUser pet;
	
	@Column(name="message")
	public String message;
	
	@Column(name="time_post")
	public DateTime time_post;
	
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

	public Comment() {
		super();
	}
	public Comment(PetUser username, String message, DateTime time_post,
			double longitude, double latitude, String type,
			String image_comment, String contact_phone) {
		super();
		this.pet = username;
		this.message = message;
		this.time_post = time_post;
		this.longitude = longitude;
		this.latitude = latitude;
		this.type = type;
		this.image_comment = image_comment;
		this.contact_phone = contact_phone;
	}

	public PetUser getUsername() {
		return pet;
	}

	public void setUsername(PetUser username) {
		this.pet = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DateTime getTime_post() {
		return time_post;
	}

	public void setTime_post(DateTime time_post) {
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
