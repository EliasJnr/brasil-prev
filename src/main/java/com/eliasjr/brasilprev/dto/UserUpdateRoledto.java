package com.eliasjr.brasilprev.dto;

import com.eliasjr.brasilprev.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRoledto {
	private Role role;
}
