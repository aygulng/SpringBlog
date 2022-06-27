package com.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.interfaces.CommentDao;
import com.blog.model.Comment;


@Repository
@Transactional
public  class CommentDaoImp implements CommentDao {

	@Autowired
	SessionFactory sf ;
	
	@Override
	public List<Comment> getCommentsByBookId(Integer id) {
		String sql= "from Comment where blog_id="+id;
	Query<Comment>  q=  sf.getCurrentSession().createQuery(sql);
 	return  q.list();

	}

	@Override
	public void saveComment(Comment comment) {
	sf.getCurrentSession().merge(comment);
	}

	
}
