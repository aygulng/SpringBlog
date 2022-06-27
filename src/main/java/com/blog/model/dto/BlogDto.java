package com.blog.model.dto;

import java.util.List;

import com.blog.model.Blog;
import com.blog.model.Comment;

public class BlogDto {

private Blog blog;
//private Blog username;
private Comment comment;
private List<Comment> commentList;

public BlogDto() {
	
}
public BlogDto(Blog book, Comment comment ,List<Comment> commentList) {
	this.blog = book;
	this.comment = comment;
	this.commentList=commentList;
//	this.username = username;
}

public Blog getBlog() {
	return blog;
}
public void setBlog(Blog book) {
	this.blog = book;
}
//public Blog getUsername() {
//	return  username;
//}
//public void setUsername(Blog username) {
//	this.username = username;
//}
public Comment getComment() {
	return comment;
}
public void setComment(Comment comment) {
	this.comment = comment;
}
public List<Comment> getCommentList() {
	return commentList;
}
public void setCommentList(List<Comment> commentList) {
	this.commentList = commentList;
}



}
