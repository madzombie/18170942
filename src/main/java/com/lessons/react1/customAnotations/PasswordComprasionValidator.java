package com.lessons.react1.customAnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lessons.react1.jsonEntity.User;

public class PasswordComprasionValidator implements ConstraintValidator<PasswordComprasion,User> {

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		System.out.println(user.getPassword() + ":"+user.getUsername()+":"+user.getPassword().contains(user.getUsername()));
		if (user.getPassword().contains(user.getUsername())==true) {
			return false;
		}
		return true;
	}

}
