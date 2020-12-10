package com.eliasjr.brasilprev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eliasjr.brasilprev.domain.User;
import com.eliasjr.brasilprev.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Ignore
	public void AsaveTest() {
		User user = new User(null, "Elias", "01287477224", "rua joao camara 202, nucleo 16 - Manaus", "jr@gmail.com",
				"123", Role.ADMINISTRATOR);
		User createdUser = userRepository.save(user);

		assertThat(createdUser.getId()).isEqualTo(1L);
	}

	@Ignore
	public void updateTest() {
		User user = new User(1L, "Elias Araujo", "01287477224", "rua 202, nucleo 16, cidade nova", "jr@gmail.com",
				"123", Role.ADMINISTRATOR);
		User updatedUser = userRepository.save(user);

		assertThat(updatedUser.getAddress()).isEqualTo("rua 202, nucleo 16, cidade nova");
	}

	@Ignore
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);

		User user = result.get();

		assertThat(user.getPassword()).isEqualTo("123");

	}

	@Ignore
	public void listTest() {
		List<User> users = userRepository.findAll();

		assertThat(users.size()).isEqualTo(1);
	}

	@Ignore
	public void loginTest() {
		Optional<User> result = userRepository.login("jr@gmail.com", "123");

		User loggedUser = result.get();

		assertThat(loggedUser.getId()).isEqualTo(1L);
	}

	@Test
	public void updateRoleTest() {
		int affectrows = userRepository.updateRole(1L, Role.SIMPLE);
		assertThat(affectrows).isEqualTo(1);
	}

}
