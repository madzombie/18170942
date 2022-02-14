package com.lessons.react1.services;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lessons.react1.jsonEntity.User;
import com.lessons.react1.repositories.UserRepository;

@Service
public class UserService {
	
	//@Autowired
	UserRepository userRepository;

	//@Autowired
	PasswordEncoder passwordEncoder;
	

	
	public UserService (UserRepository userRepository) {
		this.userRepository=userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public void save (User user) {

		user.setPassword(
		passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}
	
	public User getUser (String username) {
		return userRepository.getUser(username);
	}
	
	public User checkCred (String authString) {
		String authStringFormatted = authString.split("Basic ")[1];
		String credString = new String(Base64.getDecoder().decode(authStringFormatted));
		String[] credList = credString.split(":");
		String username = credList[0];
		String password = credList[1];

		User innoDB = userRepository.getUser(username);
		String dbPass = innoDB.getPassword();
		
		if (passwordEncoder.matches(password, dbPass)) {
			System.out.println(passwordEncoder.matches(password, dbPass));
			return innoDB;
		}

		
		return null;
	}

}
