package com.blog.interfaces;

import com.blog.model.BlogUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<BlogUser, Long> {

	BlogUser findByUsername(String username);

	BlogUser findByUsernameAndIsactive(String username, String y);

}
