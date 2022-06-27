package com.blog.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.model.Blog;
import com.blog.model.SignupForm;
import com.blog.interfaces.UserDao;
import com.blog.security.UserService;
import com.blog.service.BlogService;
import com.blog.service.BlogService2;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogService2 blogService2;
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;

	@GetMapping("/")
	public String startWeb(ModelMap model) throws ParseException {
		List<Blog> ablist = blogService.getBlogkList();;
          	model.addAttribute("blogs", ablist);
		return "home";
	}

	@GetMapping("/login")
	public String loginWeb(@ModelAttribute("sms") Object sms, ModelMap model) {
		model.addAttribute("sms", sms);
		return "login";
	}

	@GetMapping(value = "/signup")
	public String signUpForm(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	@GetMapping(value = "/success")
	public String loginSuccess() {
	String context = "home";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object[] arr = userDetails.getAuthorities().toArray();
		for (int i = 0; i < arr.length; i++) {
			String role = arr[i].toString();
			if (role.equals("ADMIN")) {
				context = "redirect:/api/v1/admin/";
			} else if (role.equals("USER")) {
				context = "redirect:/api/v2/blogs/";
			}
		}
		return context;

	}

	@PostMapping(value = "/signup")
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm,
			BindingResult bindingResult,
			ModelMap model) {

		BlogUser b_Blog_user = userDao.findByUsername(signupForm.getUsername());
		System.out.println("bindingResult : " + bindingResult.toString());
		if (!bindingResult.hasErrors()) {
			if (b_Blog_user != null) {
				model.addAttribute("sms", "Пользователь : \"" + b_Blog_user.getUsername() + "\" \t уже существует");
				return "signup";
			}
			// validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				BlogUser newBlogUser = new BlogUser();
				newBlogUser.setPassword(hashPwd);
				newBlogUser.setUsername(signupForm.getUsername());
				newBlogUser.setRole("USER");
				userService.saveUser(newBlogUser);
				model.addAttribute("sms", "Учетная запись успешно создана");

			} else {
				bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Пароли не совпадают");
				return "signup";
			}
		} else {

			return "signup";
		}
		return "signup";
	}

}
