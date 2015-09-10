package mk.ukim.finki.wp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="comments")
public class Comment extends  BaseEntity {

	@ManyToOne
	private PetUser user;
	
	@ManyToOne
	private Post post;
	
	@Column(name="message")
	private String message;
	
	@Column(name="time_comment")
	@Temporal(TemporalType.TIMESTAMP)
	public Date time_comment;
	
	@Column(name="image_comment")
	public String image_comment;

	public Comment(PetUser user, Post post, String message, Date time_comment,
			String image_comment) {
		super();
		this.user = user;
		this.post = post;
		this.message = message;
		this.time_comment = time_comment;
		this.image_comment = image_comment;
	}

	public Comment() {
		super();
	}

	public PetUser getUser() {
		return user;
	}

	public void setUser(PetUser user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime_post() {
		return time_comment;
	}

	public void setTime_post(Date time_post) {
		this.time_comment = time_post;
	}

	public String getImage_comment() {
		return image_comment;
	}

	public void setImage_comment(String image_comment) {
		this.image_comment = image_comment;
	}
	
	
	
}
