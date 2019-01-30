package vp.spring.rcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.ConversationRepository;
import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Conversation;
import vp.spring.rcs.model.User;

@Service
public class ConversationService {

	@Autowired
	private ConversationRepository conversationRepository;

	public Conversation findOne(Long id) {
		return conversationRepository.findOne(id);
	}

	public Conversation save(Conversation conversation) {
		return conversationRepository.save(conversation);
	}

	public Conversation findConversation(User firstUser, User secondUser) {
		return conversationRepository.findByFirstUserAndSecondUser(firstUser, secondUser);
	}

}
