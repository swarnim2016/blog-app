package com.springproject.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4,message = "Username should be greater than 4 characters!")
	private String name;
	@Email(message = "Invalid EmailId")
	private String email;
	@NotEmpty
	@Size(min = 3,message = "Invalid Passwod")
	private String password;
	@NotEmpty
	private String about;

}
