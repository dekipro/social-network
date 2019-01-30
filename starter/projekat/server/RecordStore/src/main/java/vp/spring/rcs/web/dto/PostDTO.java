package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Post;
import vp.spring.rcs.model.User;

public class PostDTO {
	private Long id;
	private String tekst;
	private UserDTO authorDTO;
	private List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
	
	
	public PostDTO() {
		super();
	}

	public PostDTO(Long id, String tekst, UserDTO authorDTO) {
		super();
		this.id = id;
		this.tekst = tekst;
		this.authorDTO = authorDTO;
	}
	
	public PostDTO(Post post) {
		this.id = post.getId();
		this.tekst = post.getTekst();
		this.authorDTO = new UserDTO(post.getAuthor());
	}

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

	public List<CommentDTO> getCommentsDTO() {
		return commentsDTO;
	}

	public void setCommentsDTO(List<CommentDTO> commentsDTO) {
		this.commentsDTO = commentsDTO;
	}
	
	

	
}
