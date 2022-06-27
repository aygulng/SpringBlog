package com.blog.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name="bloguser", schema="public")
public class BlogUser {

 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long user_id;

    @Column(name = "username", nullable = false,  unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "isactive", nullable = false)
    private String isactive;

   

	public BlogUser() {
    }

	public BlogUser(String username, String password, String role, String isactive) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.isactive = isactive;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
    
	
	 @Override
		public String toString() {
			return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", role=" + role
					+ ", isactive=" + isactive + "]";
		}	 
} 