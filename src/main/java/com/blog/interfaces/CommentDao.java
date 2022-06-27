package com.blog.interfaces;

import java.util.List;

import com.blog.model.Comment;

public interface CommentDao {
	public List<Comment> getCommentsByBookId(Integer id);
	public void saveComment(Comment comment);
}
