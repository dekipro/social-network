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
import vp.spring.rcs.model.Conversation;
import vp.spring.rcs.model.Message;
import vp.spring.rcs.model.User;
import vp.spring.rcs.security.TokenUtils;
import vp.spring.rcs.service.CommentService;
import vp.spring.rcs.service.ConversationService;
import vp.spring.rcs.service.MessageService;
import vp.spring.rcs.service.PostService;
import vp.spring.rcs.service.UserService;
import vp.spring.rcs.web.dto.CommentDTO;
import vp.spring.rcs.web.dto.ConversationDTO;
import vp.spring.rcs.web.dto.LoginDTO;
import vp.spring.rcs.web.dto.MessageDTO;
import vp.spring.rcs.web.dto.SortCommentById;
import vp.spring.rcs.web.dto.FriendRequestDTO;
import vp.spring.rcs.web.dto.TokenDTO;
import vp.spring.rcs.web.dto.UserDTO;

@RestController

public class ConversationController {

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

	@Autowired
	private ConversationService conversationService;

	@RequestMapping(value = "api/user/{id}/conversations", method = RequestMethod.GET)
	public ResponseEntity<ConversationDTO> findConversation(@PathVariable Long id) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);

		User currentUser = userService.findByUserUsername(username);

		User friend = userService.findOne(id);

		Conversation conversation = conversationService.findConversation(currentUser, friend);

		ConversationDTO retVal = new ConversationDTO(conversation);

		for (Message m : conversation.getMessages()) {

			MessageDTO messageDTO = new MessageDTO(m);

			retVal.getMessages().add(messageDTO);
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "api/conversations/{id}/add-message", method = RequestMethod.POST)
	ResponseEntity<MessageDTO> addMessageToConversation(@PathVariable Long id, @RequestBody MessageDTO messageDTO,
			@RequestBody UserDTO friendDTO) {

		Message message = new Message();
		message.setText(messageDTO.getTekst());

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User author = userService.findByUserUsername(username);
		message.setAuthor(author);

		Conversation conversation;
		if (id != -1) {
			conversation = conversationService.findOne(id);
		} else {
			User friend = userService.findOne(friendDTO.getId());
			conversation = new Conversation();
			conversation.setFirstUser(author);
			conversation.setSecondUser(friend);
		}
		 
		conversation.getMessages().add(message);
		

		return new ResponseEntity<>(new MessageDTO(message), HttpStatus.OK);
	}

}
