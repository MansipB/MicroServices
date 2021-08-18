package com.user.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dao.UserRepository;
import com.user.entity.UserEntity;
import com.user.exception.EntityAlreadyExistsException;
import com.user.exception.UserNameNotFoundException;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserEntity createUser(UserEntity user) throws EntityAlreadyExistsException {
		UserEntity existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new EntityAlreadyExistsException("User");
		} else {
			existingUser = userRepository.save(user);
		}
		return existingUser;
	}

	@Override
	public UserEntity getUserByUsername(String username) throws UserNameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNameNotFoundException(username);
		}
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new EntityNotFoundException("User with id : " + id + " not Found");
		}
	}

	@Override
	public UserEntity updateUser(Long userId, UserEntity user) {
		try {
			userRepository.getById(userId);
		} catch (Exception e) {
			throw new EntityNotFoundException("User with id : " + userId + " not Found");
		}
		user.setId(userId);
		return userRepository.save(user);
	}

	@Override
	public UserEntity getUserById(Long userId) {
		return userRepository.getById(userId);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void changePassword(Long userId, String password) {
		UserEntity user = getUserById(userId);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	@Override
	public void confirmUser(Long userId) {
		UserEntity confirmUser = userRepository.getById(userId);
		confirmUser.setConfirmed(true);
		userRepository.save(confirmUser);
	}

}
