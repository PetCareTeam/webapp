package mk.ukim.finki.wp.model;

import java.util.List;

public class ListComments {

	private List<Comment> comment;

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
