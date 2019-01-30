package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;

import vp.spring.rcs.model.Conversation;

public class ConversationDTO {

	private Long id;
	private List<MessageDTO> messages = new ArrayList<MessageDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

	public ConversationDTO() {
		super();
	}

	public ConversationDTO(Long id) {
		super();
		this.id = id;
		
	}
	
	public ConversationDTO(Conversation conversation) {

		this.id = conversation.getId();
	}	

}
