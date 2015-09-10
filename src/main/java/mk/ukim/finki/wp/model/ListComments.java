package mk.ukim.finki.wp.model;

import java.util.List;

public class ListComments {

	private List<Comment> comments;

	private int pictureNo;
	private int locationNo;
	
	public ListComments(List<Comment> comments, int pictureNo, int locationNo) {
		super();
		this.comments = comments;
		this.pictureNo = pictureNo;
		this.locationNo = locationNo;
	}

	public int getPictureNo() {
		return pictureNo;
	}

	public void setPictureNo(int pictureNo) {
		this.pictureNo = pictureNo;
	}

	public int getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(int locationNo) {
		this.locationNo = locationNo;
	}

	public ListComments() {
		super();
	}
	
	public ListComments(List<Comment> comments) {
		super();
		this.comments = comments;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	
}
