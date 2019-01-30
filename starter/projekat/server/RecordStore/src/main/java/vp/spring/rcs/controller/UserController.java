package vp.spring.rcs.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;
import vp.spring.rcs.service.PostService;
import vp.spring.rcs.service.UserService;
import vp.spring.rcs.web.dto.CommentDTO;
import vp.spring.rcs.web.dto.PostDTO;
import vp.spring.rcs.web.dto.UserDTO;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {

		Page<User> users = userService.findAll(pageable);

		List<UserDTO> retVal = new ArrayList<>();

		for (User user : users) {
			retVal.add(new UserDTO(user));
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findKorisnik(@PathVariable Long id) {

		User user = userService.findOne(id);

		UserDTO retVal = new UserDTO(user);
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	
//ne valja ovaj URL treba smisliti neki bolji
	@RequestMapping(value = "/friends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findFriendsPosts(@PathVariable Long id, Pageable page) {

		User friend = userService.findOne(id);
		List<Post> posts = postService.getPostsOfUser(friend,page);

		List<PostDTO> retVal = new ArrayList<>();

		for (Post p : posts) {

			PostDTO postDTO = new PostDTO(p);

			for (Comment c : p.getComments()) {
				postDTO.getCommentsDTO().add(new CommentDTO(c));
			}

			retVal.add(postDTO);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
