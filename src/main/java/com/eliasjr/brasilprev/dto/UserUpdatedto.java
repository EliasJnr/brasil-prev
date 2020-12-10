package com.eliasjr.brasilprev.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.eliasjr.brasilprev.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatedto {

	@NotBlank(message = "Name required")
	private String name;

	@NotBlank(message = "CPF required")
	private String cpf;

	@NotBlank(message = "Address required")
	private String address;

	@Email(message = "Invalid email address")
	private String email;

	@Size(min = 7, max = 99, message = "Password must br between 7 and 99")
	private String password;

	public User transformToUser() {
		return new User(null, this.name, this.cpf, this.address, this.email, this.password, null);
	}
}
