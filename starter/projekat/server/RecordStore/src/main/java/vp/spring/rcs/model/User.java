package vp.spring.rcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import vp.spring.rcs.model.user.SecurityUser;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private SecurityUser securityUser;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> friends = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> friendRequests = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> sentRequests = new HashSet<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SecurityUser getSecurityUser() {
		return securityUser;
	}

	public void setSecurityUser(SecurityUser securityUser) {
		this.securityUser = securityUser;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<User> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(Set<User> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public Set<User> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(Set<User> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public User() {
		super();
	}

}
