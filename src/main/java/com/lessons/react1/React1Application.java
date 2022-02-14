package com.lessons.react1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lessons.react1.jsonEntity.User;
import com.lessons.react1.services.UserService;

@SpringBootApplication//(exclude=SecurityAutoConfiguration.class)
public class React1Application {

	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(React1Application.class, args);
	}

	@Bean
	public  void userImport () {
		User usr = new User();
		usr.setUsername("user1");
		usr.setPassword("user1");
		usr.setDisplayname("User User");
		try {
			userService.save(usr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("http://127.0.0.1:3000");
	            }
	        };
	    }
	
}
