package com.blog.security;

import com.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.interfaces.UserDao;


@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	BCryptPasswordEncoder bpe= new BCryptPasswordEncoder();
	private final UserDao repository;
	@Autowired	
	public UserDetailServiceImpl(UserDao repository) {
	
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BlogUser current_Blog_user = repository.findByUsernameAndIsactive(username, "Y");
    	
        UserDetails user 
        = new org.springframework.security.core.userdetails.User(
        		current_Blog_user.getUsername(),
        		current_Blog_user.getPassword(),
        		true,
        		true, 
        		true,
        		true,
        	AuthorityUtils.createAuthorityList(current_Blog_user.getRole()) );
        System.out.println("ROLE: " + current_Blog_user.getRole());
        return user;
	}
	
	


}
