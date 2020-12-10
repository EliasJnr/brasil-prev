package com.eliasjr.brasilprev.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliasjr.brasilprev.domain.User;
import com.eliasjr.brasilprev.dto.UserLoginResponsedto;
import com.eliasjr.brasilprev.dto.UserLogindto;
import com.eliasjr.brasilprev.dto.UserSavedto;
import com.eliasjr.brasilprev.dto.UserUpdateRoledto;
import com.eliasjr.brasilprev.dto.UserUpdatedto;
import com.eliasjr.brasilprev.model.PageModel;
import com.eliasjr.brasilprev.model.PageRequestModel;
import com.eliasjr.brasilprev.security.JwtManager;
import com.eliasjr.brasilprev.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtManager jwtManager;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userdto) {
		User createdUser = userService.save(userdto.transformToUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserUpdatedto userdto) {
		User user = userdto.transformToUser();
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponsedto> login(@RequestBody @Valid UserLogindto user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),
				user.getPassword());
		Authentication auth = authManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);

		org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) auth
				.getPrincipal();
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(jwtManager.createToken(email, roles));
	}

	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody UserUpdateRoledto userdto) {
		User user = new User();
		user.setId(id);
		user.setRole(userdto.getRole());

		userService.updateRole(user);

		return ResponseEntity.ok().build();
	}

}
