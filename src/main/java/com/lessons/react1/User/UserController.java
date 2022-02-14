package com.lessons.react1.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.lessons.react1.jsonEntity.User;
import com.lessons.react1.services.UserService;
import com.lessons.react1.shared.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.lessons.react1.error.ApiError;


@RestController
@Validated
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	


	
	@PostMapping("/api/v1/users")
	@JsonView(View.Sensitive.class)
	public ResponseEntity<?> createUser (@Valid @RequestBody User body) {
				log.info(body.toString());
				log.info(body.getUsername());
				userService.save(body);
		return ResponseEntity.ok("user created");
		}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException (MethodArgumentNotValidException exception) {
		System.out.println("handle validation exception");
		Map<String,String> valList = new HashMap();
		/* ApiError class ;
		 * @Getter
		 *  @Setter
		 * @Builder
		 * public class ApiError {

		 *		private int status;
		 *		private String message;
		 *		private String path;
		 *		private long timeStamp = new Date().getTime();
		 *		private Map<String,String> validationErrors;
		 *}
		 */
		
		ApiError apiError =   ApiError.builder()
				.status(400)
				.message("field is empty")
				.path("/api/v1/users")
				.build();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			valList.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		apiError.setValidationErrors(valList);
		return apiError;
		
	}
	

}
