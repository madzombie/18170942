package com.lessons.react1.error;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class ApiError {

	private int status;
	private String message;
	private String path;
	private long timeStamp = new Date().getTime();
	private Map<String,String> validationErrors;
}
