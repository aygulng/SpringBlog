package com.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="comment", schema="public")
public class Comment {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	comment_id;
	private String  description;
//	private Long user_id;
	private String  username;
	private Integer blog_id;
	
	public Comment() {
		
	}

	public Comment(Integer comment_id, String description, String username, Integer blog_id) {
		super();
		this.comment_id = comment_id;
		this.description = description;
		this.username = username;
		this.blog_id = blog_id;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}


	
	
	
}
