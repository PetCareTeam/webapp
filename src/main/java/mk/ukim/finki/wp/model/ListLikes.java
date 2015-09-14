package mk.ukim.finki.wp.model;

import java.util.List;
/* ovaa klasa se koristi, za da se dobie rezultat, dali eden korisnik mozhe da lajkne nekoj post, t.e. ako vekje go lajknal prethodno
 * ne mozhe da go lajkne pak. */
public class ListLikes {

	private List<Likedpost> likes;

	private boolean result;
	
	public ListLikes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListLikes(List<Likedpost> likes) {
		super();
		this.likes = likes;
	}

	public List<Likedpost> getLikes() {
		return likes;
	}

	public void setLikes(List<Likedpost> likes) {
		this.likes = likes;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}
