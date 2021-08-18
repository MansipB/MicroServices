package com.apigateway.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apigateway.dao.UserRepository;
import com.apigateway.entity.UserEntity;
import com.apigateway.exception.UserAlreadyExistsException;
import com.apigateway.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserEntity registerUser(UserEntity user) throws UserAlreadyExistsException {
		UserEntity existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserAlreadyExistsException();
		} else {
			existingUser = userRepository.save(user);
		}
		return existingUser;
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	@Override
	public UserEntity getUserById(Long userId) {
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if (!userEntity.isPresent()) {
			throw new EntityNotFoundException();
		}
		return userEntity.get();
	}

}
