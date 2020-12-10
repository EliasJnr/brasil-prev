package com.eliasjr.brasilprev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasjr.brasilprev.domain.User;
import com.eliasjr.brasilprev.repository.UserRepository;
import com.eliasjr.brasilprev.service.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		return userRepository.save(user);
	}

	public User update(User user) {
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		return userRepository.save(user);
	}

	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.get();
	}

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}

}
