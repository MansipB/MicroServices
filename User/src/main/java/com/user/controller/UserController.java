package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.UserEntity;
import com.user.exception.CustomUserNotFoundException;
import com.user.exception.EntityAlreadyExistsException;
import com.user.exception.UserNameNotFoundException;
import com.user.service.impl.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws EntityAlreadyExistsException {
		user.setUserType("NORMAL");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserEntity createUser = userService.createUser(user);
		return ResponseEntity.ok().body(createUser);

	}

	@GetMapping("/{username}")
	public ResponseEntity<UserEntity> getuser(@PathVariable("username") String username)
			throws UserNameNotFoundException {
		UserEntity user = userService.getUserByUsername(username);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserEntity> getuserByEmail(@PathVariable("email") String email)
			throws CustomUserNotFoundException {
		UserEntity user = userService.getUserByEmail(email);
		if (user == null) {
			throw new CustomUserNotFoundException("email", email);
		}
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteuser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().body("User with id: " + id + " succesfully deleted");
	}

	@PostMapping("/{id}")
	public ResponseEntity<UserEntity> updateuser(@RequestBody UserEntity user, @PathVariable("id") Long id) {
		return ResponseEntity.ok().body(userService.updateUser(id, user));
	}

	@PostMapping("/{id}/change-password")
	public ResponseEntity<String> changePassword(@RequestBody String password, @PathVariable("id") Long id) {
		userService.changePassword(id, password);
		return ResponseEntity.ok().body("Password Changed");
	}

	@GetMapping("{id}/confirm-user")
	public ResponseEntity<String> confirmUser(@PathVariable("id") Long id) {
		userService.confirmUser(id);
		return ResponseEntity.ok().body("User Confirmed");
	}
}
