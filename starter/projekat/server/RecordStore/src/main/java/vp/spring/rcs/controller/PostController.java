package vp.spring.rcs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PostController {

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

	@RequestMapping(value = "api/user/posts/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> findCommentsForPost(@PathVariable Long id) {

		Post post = postService.findOne(id);

		List<Comment> comments = commentService.getCommentsOfPost(post);

		List<CommentDTO> retVal = new ArrayList<>();

		for (Comment c : comments) {
			CommentDTO commentDTO = new CommentDTO(c);
			

			retVal.add(commentDTO);
		}

		Collections.sort(retVal, new SortCommentById());

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
