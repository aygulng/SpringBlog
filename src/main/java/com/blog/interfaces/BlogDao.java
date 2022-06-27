package com.blog.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.blog.model.Blog;
import com.blog.model.Comment;

public interface BlogDao {

	
	public List<Blog> getBlogList();
	public void saveBlog(Blog book) ;
	public void deleteById(Integer book_id) ;
	public void updateBlog(Blog book) ;
	public Blog findBlogById(Integer id);
}
