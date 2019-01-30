package vp.spring.rcs.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.PostRepository;
import vp.spring.rcs.data.UserRepository;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(page);
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.delete(id);

	}

	public User findByUserUsername(String username) {
		User user = userRepository.findBySecurityUserUsername(username);
		return user;
	}

	public Post addPost(String username, Post post) {
		User user = findByUserUsername(username);
		Post p = new Post();
		p.setAuthor(user);
		p.setTekst(post.getTekst());
	    p = postRepository.save(post);
		return p;
	}
	
	public Set<User>findFriends(User user){
		return user.getFriends();
	}
}
