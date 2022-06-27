package com.blog.repository;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.interfaces.BlogDao;
import com.blog.model.Blog;

@Repository
@Transactional
public class BlogDaoImp implements BlogDao {


	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Blog> getBlogList() {
		List<Blog> blogList = null;
		try {
			String sql = "from Blog order by 1 desc";
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery(sql, Blog.class);
			System.out.println(q.list().get(0).toString());
			blogList = (List<Blog>) q.list();
		} catch (Exception e) {
			System.out.println(e);
		}
		return blogList;

	}


	@Override
	public void saveBlog(Blog book) {
		try {
			System.out.println("date print : " + book.getCreateat());
			sessionFactory.getCurrentSession().merge(book);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("save_book_error :" + e);

		}

	}

	@Override
	public void deleteById(Integer id) {
		String sql = "delete from Blog where blog_id=" + id;
		int i = sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		System.out.println("delete print : " + i);
	}

	@Override
	public void updateBlog(Blog book) {
		try {
			System.out.println("date print : " + book.getCreateat());
			sessionFactory.getCurrentSession().merge(book);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update_blog_error :" + e);
		}

	}

	@Override
	public Blog findBlogById(Integer id) {
		String sql = "from Blog where blog_id=" + id;
		return (Blog) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();

	}
}
