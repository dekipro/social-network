package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByAuthorOrderByIdDesc(User author, Pageable page);

}
