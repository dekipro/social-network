package vp.spring.rcs.web.dto;

import vp.spring.rcs.model.user.SecurityUser;

public class LoginDTO {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public LoginDTO() {
		
	}
	
	
	public LoginDTO(Long id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public LoginDTO(SecurityUser user) {
		this.id=user.getId();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
