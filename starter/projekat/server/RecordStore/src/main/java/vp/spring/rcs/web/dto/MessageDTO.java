package vp.spring.rcs.web.dto;

import vp.spring.rcs.model.Message;

public class MessageDTO {

	private Long id;
	private String tekst;
	private UserDTO authorDTO;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public UserDTO getAuthorDTO() {
		return authorDTO;
	}
	public void setAuthorDTO(UserDTO authorDTO) {
		this.authorDTO = authorDTO;
	}
	
	public MessageDTO(Long id, String tekst, UserDTO authorDTO) {
		this.id = id;
		this.tekst = tekst;
		this.authorDTO = authorDTO;
	}
	
	public MessageDTO(Message message) {
		this.id = message.getId();
		this.tekst = message.getText();
		this.authorDTO = new UserDTO(message.getAuthor());
	}
	
	
}
