package vp.spring.rcs.web.dto;

import java.util.HashSet;
import java.util.Set;

import vp.spring.rcs.model.User;

public class UserDTO {

	private Long id;
	
	private LoginDTO loginDto;
	
	private Set<UserDTO> friendRequests = new HashSet<UserDTO>();
	
	private Set<UserDTO> sentRequests = new HashSet<UserDTO>();



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoginDTO getLoginDto() {
		return loginDto;
	}

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, LoginDTO loginDto) {
		this.id = id;
		this.loginDto = loginDto;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.loginDto = new LoginDTO(user.getSecurityUser());
	}	
}
