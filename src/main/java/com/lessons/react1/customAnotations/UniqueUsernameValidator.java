package com.lessons.react1.customAnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lessons.react1.repositories.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String>{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		
		if (userRepository.getUser(username)!=null) {
			return false;
		}

		return true;
	}

}
