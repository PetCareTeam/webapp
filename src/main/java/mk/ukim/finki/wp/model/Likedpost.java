package mk.ukim.finki.wp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name="likedPosts")
public class Likedpost extends BaseEntity{
	@ManyToOne
	private PetUser liker;
	
	@ManyToOne
	private Post post;

	public PetUser getLiker() {
		return liker;
	}

	public void setLiker(PetUser liker) {
		this.liker = liker;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Likedpost() {
		super();
	}

	public Likedpost(PetUser liker, Post post) {
		super();
		this.liker = liker;
		this.post = post;
	}
	
	
	
	
}
