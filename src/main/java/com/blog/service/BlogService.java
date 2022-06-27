 package com.blog.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.dto.BlogDto;
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.repository.BlogDaoImp;
import com.blog.repository.CommentDaoImp;


 @Service
 public class BlogService {

     @Autowired
     BlogDaoImp blogDaoImp;
     @Autowired
     CommentDaoImp commentDaoImp;

     public List<Blog> getBlogkList() throws ParseException {
         List<Blog> list_a = blogDaoImp.getBlogList();
         List<Blog> list_b = null;
         DateFormat ds = new SimpleDateFormat("DD-MM-yyyy");
         if (list_a != null) {
             list_b = new ArrayList<>();
             for (Blog ab : list_a) {
                 Date d1 = new SimpleDateFormat("yyyy-MM-DD").parse(ab.getCreateat());
                 ab.setCreateat(ds.format(d1));
                 list_b.add(ab);
             }
         }
         return list_a;
     }

     public void updateBlog(Blog blog, String status ) {
         if(status.equals("Y")) {
             blog.setIsactive("N");
         }else if(status.equals("N")) {
             blog.setIsactive("Y");
         }
     blogDaoImp.updateBlog(blog);

     }

     public Blog findBlogById(Integer id) {
         return blogDaoImp.findBlogById(id);

     }

     public void deleteById(Integer id) {
         blogDaoImp.deleteById(id);

     }

     public BlogDto bookDetailsById(Integer id) {
      BlogDto blogDto=new BlogDto();
      List<Comment> clist=commentDaoImp.getCommentsByBookId(id);

      blogDto.setBlog(this.findBlogById(id));
      blogDto.setCommentList(clist);

         return blogDto;
     }

     public void saveComment(Comment comment) {
         commentDaoImp.saveComment(comment);

     }



 }
