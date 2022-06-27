package com.blog.config;


import com.blog.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan("com.blog")
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		@Autowired
	    private UserDetailServiceImpl userDetailsService;
	    
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http
	    		.authorizeRequests().antMatchers("/css/**", "/js/**", "/signup","/h2-console/**","/h2-console/login.jsp?jsessionid**", "/saveuser").permitAll() //
	    			.antMatchers("/api/v1/admin/**").hasAnyAuthority("ADMIN")
	    			.antMatchers("/api/v2/blogs/**").hasAnyAuthority("ADMIN", "USER")
	    			.and()
	    			.authorizeRequests()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/success", true)
	                .permitAll()
	            	.and()
	            .logout()
	            .logoutSuccessUrl("/")
	            	.permitAll()
	            	.and();
	    }
		
		 @Autowired
		 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		    	auth.userDetailsService(userDetailsService)
		    	    .passwordEncoder(new BCryptPasswordEncoder() );
		    }		
		
		
		
}
