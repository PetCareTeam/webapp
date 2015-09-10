package mk.ukim.finki.wp.model;

import java.util.List;

public class ListPosts {

	private List<Post> posts;

	private int pictureNo;
	private int locationNo;
	
	public ListPosts(List<Post> posts, int pictureNo, int locationNo) {
		super();
		this.posts = posts;
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

	public ListPosts() {
		super();
	}
	
	public ListPosts(List<Post> posts) {
		super();
		this.posts = posts;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	
	
}
