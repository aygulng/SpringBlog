package com.blog.controller;

import java.text.ParseException;
import java.util.List;

import com.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.model.dto.BlogDto;
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.security.UserService;
import com.blog.service.BlogService;
import com.blog.service.BlogService2;
import com.blog.service.BloggerService;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogService2 blogService2;
	@Autowired
	BloggerService bloggerService;
	@Autowired
	UserService userService;

	@GetMapping(value = "/")
	public String blogsForAdmin(ModelMap model) throws ParseException {
		List<Blog> blogList = blogService.getBlogkList();
		model.addAttribute("blogs", blogList);
		return "admin";
	}

//	@GetMapping(value = "/blog/{id}/{status}")
//	public String approveAndHideBlog(@PathVariable("id") Integer id, @PathVariable("status") String status) {
//		Blog blog = blogService.findBlogById(id);
//
//		blogService.updateBlog(blog, status);
//		return "redirect:/api/v1/admin/";
//	}

	@GetMapping(value = "/blog/delete/{id}")
	public String deleteBlog(@PathVariable("id") Integer id) {
		blogService.deleteById(id);
		return "redirect:/api/v1/admin/";
	}

//	@GetMapping(value = "/ac/{id}/{status}")
//	public String approveAndHideAccount(@PathVariable("id") long id, @PathVariable("status") String status,
//			ModelMap model) {
//		BlogUser blogUser = bloggerService.findBloggerById(id);
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = userDetails.getUsername();
//		if (blogUser != null && username.equals(blogUser.getUsername())) {
//			model.addAttribute("ams", "");
//			return "admin";
//		} else if (blogUser != null) {
//			bloggerService.updateBlogUser(blogUser, status);
//		}
//		return "redirect:/api/v1/admin/blogger";
//	}

	@GetMapping(value = "/ac/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, ModelMap model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		String username2 = bloggerService.findBloggerById(id).getUsername();
		if (username.equals(username2)) {
			model.addAttribute("ams", "");
			return "admin";
		}
		bloggerService.deleteBloggerById(id);
		return "admin";
	}

	@GetMapping(value = "/blogger")
	public String viewBlogger(ModelMap model) {
		List<BlogUser> buser = bloggerService.getAllBlogger();
		model.addAttribute("buser", buser);
		return "admin";
	}

	@GetMapping(value = "ac/create/form")
	public String acCreateForm(Model model) {
		model.addAttribute("bloggerForm", new BlogUser());
		return "acform";
	}

	@PostMapping(value = "ac/create")
	public String acCreateForm(@ModelAttribute("bloggerForm") BlogUser blogger, ModelMap model) {
		if (blogger.getRole().equals("") | blogger.getIsactive().equals("")) {
			model.addAttribute("bloggerForm", new BlogUser());
			model.addAttribute("sms", "РОЛЬ или статус должны быть выбраны");
		} else {
			bloggerService.saveUser(blogger);
			model.addAttribute("sms", "Аккаунт успешно создан");
			
		}
		return "acform";
	}
	
	
	
	@GetMapping(value = "/blog/view/{id}")
	public String viewBook(@PathVariable Integer id, Model model) {
		BlogDto blogDto = blogService.bookDetailsById(id);
		Comment cmt = new Comment();
		cmt.setBlog_id(id);
		model.addAttribute("dto", blogDto);
		model.addAttribute("cmt", cmt);
		return "admin_comment";
	}
	
	@PostMapping(value = "/blog/comment")
	public String saveComment(@ModelAttribute Comment comment, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		System.out.println("ctx user name " + username);
		BlogUser adu 	= userService.findByUsername(username);
		Integer id 		= comment.getBlog_id();
		comment.setUsername(username);
		blogService.saveComment(comment);
		BlogDto blogDto = blogService.bookDetailsById(id);
		model.addAttribute("dto", blogDto);
		return "redirect:/api/v1/admin/blog/view/" + id;

	}
	
	@GetMapping("/form/{id}")
	public String getBookForm(@PathVariable("id") Integer id, @ModelAttribute Blog blog, Model model) {
		if (id > 0) {
//			 id= book.getAd_book_id();
			blog = blogService.findBlogById(id);
			model.addAttribute("info", "Изменить");
			model.addAttribute("ctx", "update");
			model.addAttribute("blog", blog);
			return "admin_form";
		} else {
			model.addAttribute("info", "Создать");
			model.addAttribute("ctx", "create");
			model.addAttribute("blog", blog);
			return "admin_form";
		}

	}

	@PostMapping("/create")
	public String saveBook(@ModelAttribute Blog book) {
		blogService2.saveBlog(book);
		return "redirect:/api/v1/admin/";
	}

	@PostMapping(value = "/update")
	public String updateBook(@ModelAttribute Blog book) {
		blogService2.updateBlog(book);
		return "redirect:/api/v1/admin/myblogs/";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable Integer id) {
		blogService.deleteById(id);
		return "redirect:/api/v1/admin/myblogs";
	}
	
	
	@GetMapping("/myblogs")
	public String getMyBlogs(ModelMap model) throws ParseException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		List<Blog> ablist = blogService2.getMyBlogkList(username);
		
		model.addAttribute("myblogs", ablist);
  //	model.addAttribute(new Ad_book());
		return "admin";
	}
	
	
	
	
	
	
	
}