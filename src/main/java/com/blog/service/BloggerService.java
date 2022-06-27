package com.blog.service;

import java.util.List;

import com.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.repository.BloggerDao;

@Service
public class BloggerService {
	
	BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
	@Autowired
	BloggerDao bloggerDao;

	public List<BlogUser> getAllBlogger() {
		return bloggerDao.getBloggerList();
	}

	public BlogUser findBloggerById(long id) {
		return bloggerDao.findBloggerById(id);
	}

//	public void updateBlogUser(BlogUser blogUser, String status) {
//		if (status.equals("Y")) {
//			blogUser.setIsactive("N");
//		} else if (status.equals("N")) {
//			blogUser.setIsactive("Y");
//		}
//		bloggerDao.updateBlogger(blogUser);
//	}

	public void deleteBloggerById(long id) {
		bloggerDao.deleteById(id);

	}

	public void saveUser(BlogUser blogger) {
		blogger.setPassword(bpe.encode(blogger.getPassword()));
        bloggerDao.saveBloguser(blogger);
		
	}
}
