package com.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="blog", schema="public")
public class Blog {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blog_id;

	private String title;

	@Column(name="details" , length = 65535)    ///, columnDefinition="TEXT")
	@Type(type="text")
	private String details;

	private String username;
	private String updateat;
	private String createat;
	private String isactive;

	public Blog() {
	
	}

	public Blog( String title, String details, String username,  String createat ,String updateat, String isactive) {
		this.updateat=updateat;
		this.isactive = isactive;
		this.title = title;
		this.details = details;
		this.username = username;
		
		this.createat = createat;
	}

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUpdateat() {
		return updateat;
	}

	public void setUpdateat(String updateat) {
		this.updateat = updateat;
	}

	public String getCreateat() {
		return createat;
	}

	public void setCreateat(String createdat) {
		this.createat = createdat;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "Blog [blog_id=" + blog_id + ", title=" + title + ", details=" + details + ", username=" + username
				+ ", updateat=" + updateat + ", createat=" + createat + ", isactive=" + isactive + "]";
	}


	
}
