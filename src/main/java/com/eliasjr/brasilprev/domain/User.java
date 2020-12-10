package com.eliasjr.brasilprev.domain;

import com.eliasjr.brasilprev.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private String cpf;
	private String address;
	private String email;
	private String password;
	private Role role;
}
