package mk.ukim.finki.wp.repository;

import java.util.List;

import mk.ukim.finki.wp.model.Likedpost;
import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPostsRepository extends JpaRepository<Likedpost, Long> {
		public List<Likedpost> findByPost(Post post);
	}
	

