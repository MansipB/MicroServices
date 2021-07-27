package com.user.service;

import com.user.entity.UserEntity;
import com.user.exception.EntityAlreadyExistsException;
import com.user.exception.UserNameNotFoundException;

public interface UserService {

	public UserEntity createUser(UserEntity user) throws EntityAlreadyExistsException;

	public UserEntity getUserById(Long userId);

	public UserEntity getUserByUsername(String username) throws UserNameNotFoundException;

	public void deleteUser(Long id);

	public UserEntity updateUser(Long userId, UserEntity user);

	void changePassword(Long userId, String password);

	void confirmUser(Long userId);

	UserEntity getUserByEmail(String email);
}
