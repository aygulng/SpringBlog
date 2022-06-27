package com.blog.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.model.Blog;
import com.blog.interfaces.BlogRepo2;

@Service
public class BlogService2 {
	@Autowired
	BlogRepo2 blogRepo2;
//
//	public List<Blog> getActiveBlogkList() {
//		List<Blog> activeBlog_list = null;
//		activeBlog_list = blogRepo2.findByIsactive("Y");
//		return activeBlog_list;
//	}

	public List<Blog> getMyBlogkList(String username) {
		List<Blog> blog_list = null;
		blog_list = blogRepo2.findByUsername(username);
		return blog_list;
	}

	public void saveBlog(Blog blog) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		Date date = new Date();
		System.out.println("Create Date || " + date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current_date = df.format(date);
		blog.setIsactive("Y");
		blog.setCreateat(current_date);
		blog.setUsername(username);
//		blog.setUpdateat("");
		blogRepo2.save(blog);
	}

	public void updateBlog(Blog blog) {
		Date date = new Date();
		System.out.println("Update Date || " + date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current_date = df.format(date);
		blog.setUpdateat(current_date);
		blogRepo2.save(blog);
	}

}
