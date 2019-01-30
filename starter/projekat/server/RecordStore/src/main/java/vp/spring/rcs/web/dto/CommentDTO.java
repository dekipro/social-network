package vp.spring.rcs.web.dto;

import vp.spring.rcs.model.Comment;

public class CommentDTO {

	private Long id;
	private String text;
	private UserDTO authorDTO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UserDTO getAuthorDTO() {
		return authorDTO;
	}
	public void setAuthorDTO(UserDTO authorDTO) {
		this.authorDTO = authorDTO;
	}
	public CommentDTO() {
		super();
	}
	public CommentDTO(Long id, String text, UserDTO authorDTO) {
		super();
		this.id = id;
		this.text = text;
		this.authorDTO = authorDTO;
	}
	
	public CommentDTO(Comment comment) {
          this.id = comment.getId();
          this.text = comment.getText();
          this.authorDTO = new UserDTO(comment.getAuthor());
	}
	
}
