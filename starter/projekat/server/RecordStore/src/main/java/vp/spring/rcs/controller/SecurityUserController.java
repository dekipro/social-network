package vp.spring.rcs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;
import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Message;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;
import vp.spring.rcs.security.TokenUtils;
import vp.spring.rcs.service.CommentService;
import vp.spring.rcs.service.PostService;
import vp.spring.rcs.service.UserService;
import vp.spring.rcs.web.dto.CommentDTO;
import vp.spring.rcs.web.dto.LoginDTO;
import vp.spring.rcs.web.dto.PostDTO;
import vp.spring.rcs.web.dto.SortCommentById;
import vp.spring.rcs.web.dto.FriendRequestDTO;
import vp.spring.rcs.web.dto.TokenDTO;
import vp.spring.rcs.web.dto.UserDTO;

@RestController
public class SecurityUserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			String genToken = tokenUtils.generateToken(details);
			return new ResponseEntity<TokenDTO>(new TokenDTO(genToken), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "api/user/posts", method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findPosts(Pageable page) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		System.out.println(username);

		User author = userService.findByUserUsername(username);
		List<Post> posts = postService.getPostsOfUser(author,page);

 

		List<PostDTO> retVal = new ArrayList<>();

		 

		if (posts.size() != 0) {
			for (Post p : posts) {
				PostDTO postDTO = new PostDTO(p);
				
				System.out.println(postDTO.getTekst());

				List<Comment> comments = commentService.getCommentsOfPost(p);
				

				if (comments.size() != 0) {

					for (Comment c : comments) {
						postDTO.getCommentsDTO().add(new CommentDTO(c));
					}
					Collections.sort(postDTO.getCommentsDTO(), new SortCommentById());

				}

					retVal.add(postDTO);
				}
			
		}
		

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "api/user/sentFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<List<FriendRequestDTO>> findSentFriendRequests() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUserUsername(username);
		Set<User> sentFriendRequests = user.getSentRequests();

		List<FriendRequestDTO> retVal = new ArrayList<>();

		for (User sr : sentFriendRequests) {
			retVal.add(new FriendRequestDTO(sr));
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	// mora u angularu da se implementira
	@RequestMapping(value = "api/user/recievedFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findRecievedFriendRequests() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUserUsername(username);
		Set<User> friendRequests = user.getFriendRequests();

		List<UserDTO> retVal = new ArrayList<>();

		if (friendRequests != null) {

			for (User fr : friendRequests) {
				retVal.add(new UserDTO(fr));
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "api/user/add-post", method = RequestMethod.POST)
	ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
		Post post = new Post();
		post.setTekst(postDTO.getTekst());

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User author = userService.findByUserUsername(username);
		post.setAuthor(author);
		postService.save(post);

		return new ResponseEntity<>(postDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "api/user/sendFriendRequest", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> sendFriendRequest(@RequestBody UserDTO friendDTO) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUserUsername(username);

		User friendToSendRequest = userService.findOne(friendDTO.getId());

		user.getSentRequests().add(friendToSendRequest);
		friendToSendRequest.getFriendRequests().add(user);

		userService.save(user);
		userService.save(friendToSendRequest);

		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@RequestMapping(value = "api/user/addfriend", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> addFriend(@RequestBody UserDTO friendDTO) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUserUsername(username);
		User friendToAdd = userService.findOne(friendDTO.getId());

		user.getFriends().add(friendToAdd);
		friendToAdd.getFriends().add(user);

		user.getFriendRequests().remove(friendToAdd);
		friendToAdd.getSentRequests().remove(user);

		userService.save(user);
		userService.save(friendToAdd);

		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@RequestMapping(value = "api/user/friends", method = RequestMethod.GET)
	public ResponseEntity<Set<UserDTO>> findFriends() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUserUsername(username);

		Set<User> friends = userService.findFriends(user);

		Set<UserDTO> retVal = new HashSet<>();
		for (User friend : friends) {
			retVal.add(new UserDTO(friend));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "api/post/{id}/add-comment", method = RequestMethod.POST)
	public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User commentAuthor = userService.findByUserUsername(username);
		User postAuthor = postService.findOne(id).getAuthor();

		Comment commentToAdd = new Comment();
		commentToAdd.setText(comment.getText());
		commentToAdd.setAuthor(commentAuthor);

		Post post = postService.findOne(id);

		commentToAdd.setPost(post);

		commentService.save(commentToAdd);

		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}

}
