package vp.spring.rcs.web.dto;

import vp.spring.rcs.model.User;

public class FriendRequestDTO {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FriendRequestDTO() {
		super();
	}

	public FriendRequestDTO(Long id) {
		super();
		this.id = id;
	}
	
	public FriendRequestDTO(User user) {
		this.id = user.getId();
 	}
}
