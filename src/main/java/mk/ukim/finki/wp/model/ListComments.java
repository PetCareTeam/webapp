package mk.ukim.finki.wp.model;

import java.util.List;

public class ListComments {

	private List<Comment> comment;
	
	private Post post;
	

	public ListComments(List<Comment> comment, Post post) {
		super();
		this.comment = comment;
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public ListComments(List<Comment> comment) {
		super();
		this.comment = comment;
	}

	public ListComments() {
		super();
	}
	
}
