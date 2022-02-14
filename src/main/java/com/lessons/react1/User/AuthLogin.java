package com.lessons.react1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lessons.react1.error.ApiError;
import com.lessons.react1.jsonEntity.User;
import com.lessons.react1.services.UserService;
import com.lessons.react1.shared.View;

@RestController
public class AuthLogin {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/v1/login")
	@JsonView(View.Base.class)
	public ResponseEntity<?> auth (@RequestHeader(name="Authorization",required=false) String authString) {
		System.out.println(authString);
		ApiError apiError= ApiError.builder()
				.status(401)
				.message("UNAUTHORIZED Request")
				.path("/api/v1/login")
				.build();
		if (authString == null) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		User innoUser = userService.checkCred(authString);
		if (innoUser==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}

		return ResponseEntity.ok().body(innoUser);
	}

}
