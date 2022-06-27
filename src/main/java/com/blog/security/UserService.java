package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.BlogUser;
import com.blog.interfaces.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
  public void	saveUser(BlogUser blogUser){
		blogUser.setIsactive("Y");
		userDao.save(blogUser);
	}
  public BlogUser findByUsername(String username) {
	  
	  return userDao.findByUsernameAndIsactive(username, "Y");
  }



}
