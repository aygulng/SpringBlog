package com.blog.controller;

import java.text.ParseException;
import java.util.List;

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
import com.blog.model.BlogUser;
import com.blog.security.UserService;
import com.blog.service.BlogService;
import com.blog.service.BlogService2;

@Controller
@RequestMapping("/api/v2/blogs")
public class BloggerController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogService2 blogService2;
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String getAllBlogs(ModelMap model) throws ParseException {
		List<Blog> ablist = blogService.getBlogkList();
		model.addAttribute("blogs", ablist);
		return "blogger";
	}

	@GetMapping("/form/{id}")
	public String getBookForm(@PathVariable("id") Integer id, @ModelAttribute Blog blog, Model model) {
		if (id > 0) {
			blog = blogService.findBlogById(id);
			model.addAttribute("info", "Изменить");
			model.addAttribute("ctx", "update");
			model.addAttribute("blog", blog);
			return "input_form";
		} else {
			model.addAttribute("info", "Создать ");
			model.addAttribute("ctx", "create");
			model.addAttribute("blog", blog);
			return "input_form";
		}

	}

	@PostMapping("/create")
	public String saveBook(@ModelAttribute Blog book) {
		blogService2.saveBlog(book);
		return "redirect:/api/v2/blogs/";
	}

	@PostMapping(value = "/update")
	public String updateBook(@ModelAttribute Blog book) {
		blogService2.updateBlog(book);
		return "redirect:/api/v2/blogs/";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable Integer id) {
		blogService.deleteById(id);
		return "redirect:/api/v2/blogs/";
	}

	@GetMapping(value = "/view/{id}")
	public String viewBook(@PathVariable Integer id, Model model) {
		BlogDto blogDto = blogService.bookDetailsById(id);
		Comment cmt = new Comment();
		cmt.setBlog_id(id);
		model.addAttribute("dto", blogDto);
		model.addAttribute("cmt", cmt);
		return "blogger_comment";
	}

	@PostMapping(value = "/comment")
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
		return "redirect:/api/v2/blogs/view/"+id;

	}

	@GetMapping("/myblogs")
	public String getMyBlogs(ModelMap model) throws ParseException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		List<Blog> ablist = blogService2.getMyBlogkList(username);
		
		model.addAttribute("myblogs", ablist);
		return "blogger";
	}

}
