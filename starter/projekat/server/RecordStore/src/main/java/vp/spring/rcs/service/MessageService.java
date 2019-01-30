package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.MessageRepository;
import vp.spring.rcs.model.Message;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message save(Message message) {
		return messageRepository.save(message);
	}

	

}
