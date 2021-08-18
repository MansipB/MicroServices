package com.apigateway.service;

import com.apigateway.entity.UserEntity;
import com.apigateway.exception.UserAlreadyExistsException;

public interface UserService {

	public UserEntity registerUser(UserEntity user) throws UserAlreadyExistsException;

	public UserEntity getUserById(Long userId);

	public UserEntity getUserByUsername(String username);

}
