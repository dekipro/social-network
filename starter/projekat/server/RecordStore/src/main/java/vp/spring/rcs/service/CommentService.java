package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.CommentRepository;
import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment findOne(Long id) {
		return commentRepository.findOne(id);
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void remove(Long id) {
		commentRepository.delete(id);

	}

	public List<Comment> getCommentsOfPost(Post post) {

		return commentRepository.findByPostIdOrderByIdAsc(post.getId());
	}

}
