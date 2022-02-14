package com.lessons.react1.jsonEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.lessons.react1.customAnotations.PasswordComprasion;
import com.lessons.react1.customAnotations.UniqueUsername;
import com.lessons.react1.shared.View;

@Entity
@Data
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=4, max=255)
	@UniqueUsername
	@JsonView(View.Base.class)
	private String username;
	
	@NotNull
	@Size(min=4,max=255)
	@JsonView(View.Base.class)
	private String displayname;
	
	@NotNull
	@Size(min=4,max=255)
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	@JsonView(View.Sensitive.class)
	private String Password;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", displayname=" + displayname + ", password=" + Password + "]";
	}

}
