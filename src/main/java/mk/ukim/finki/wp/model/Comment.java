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
@Table(name="comments")
public class Comment extends BaseEntity {
	
	@ManyToOne
	private PetUser pet;
	
	@Column(name="message")
	public String message;
	
	@Column(name="time_post")
	@Temporal(TemporalType.TIMESTAMP)
	public Date time_post;
	
	@Column(name="longitude")
	public double longitude;
	
	@Column(name="latitude")
	public double latitude;
	
	@Column(name="type")
	public String type;
	
	@Lob
	@Column(name="image_comment")
	public byte[] image_comment;
	
	@Column(name="contact_phone")
	public String contact_phone;

	public Comment() {
		super();
	}
	public Comment(PetUser username, String message, Date time_post,
			double longitude, double latitude, String type,
			byte[] image_comment, String contact_phone) {
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

	public byte[] getImage_comment() {
		return image_comment;
	}
	public void setImage_comment(byte[] image_comment) {
		this.image_comment = image_comment;
	}
	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	
	
	
}
