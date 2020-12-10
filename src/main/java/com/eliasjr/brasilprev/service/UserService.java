package com.eliasjr.brasilprev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eliasjr.brasilprev.domain.User;
import com.eliasjr.brasilprev.exception.NotFoundException;
import com.eliasjr.brasilprev.model.PageModel;
import com.eliasjr.brasilprev.model.PageRequestModel;
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
		return result.orElseThrow(() -> new NotFoundException("There are not user with id = " + id));
	}

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public PageModel<User> listAllOnLazyMode(PageRequestModel pr) {
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<User> page = userRepository.findAll(pageable);
		PageModel<User> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(),
				page.getContent());
		return pm;
	}

	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> result = userRepository.login(email, password);
		return result.orElseThrow(() -> new NotFoundException("There are not user foud"));
	}

}
