package vp.spring.rcs.service;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.PostRepository;
import vp.spring.rcs.data.UserRepository;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;

@Service
public class PostService {
	 @Autowired
	  private PostRepository postRepository;
	  
	  public List<Post> findAll() {
			return postRepository.findAll();
		}
		
		public Page<Post> findAll(Pageable page) {
			return postRepository.findAll(page);
		}

		public Post findOne(Long id) {
			return postRepository.findOne(id);
		}

		public Post save(Post post) {
			return postRepository.save(post);
		}

		public void remove(Long id) {
			postRepository.delete(id);
			
		}
		
		public List<Post>getPostsOfUser(User author, Pageable page){
			
			return postRepository.findByAuthorOrderByIdDesc(author, page);
		}
}
