package com.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.dao.UserRepository;
import com.user.entity.UserEntity;
import com.user.exception.EntityAlreadyExistsException;
import com.user.exception.UserNameNotFoundException;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	public UserServiceImpl userService;

	@Mock
	public UserRepository userRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Mock
	BCryptPasswordEncoder passwordEncoder;

	@Test
	public void createUserTest() throws EntityAlreadyExistsException {
		UserEntity user = new UserEntity(1, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", true);
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
		Mockito.when(userRepository.save(user)).thenAnswer(invocation -> invocation.getArgument(0));
		UserEntity savedUser = userService.createUser(user);
		assertThat(savedUser).isNotNull();
		verify(userRepository).findByUsername("test1");
		verify(userRepository).save(user);

	}

	@Test
	public void shouldThrowErrorWhencreateUserTest() throws EntityAlreadyExistsException {
		UserEntity user = new UserEntity(1, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", true);
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		assertThrows(EntityAlreadyExistsException.class, () -> userService.createUser(user));
		verify(userRepository).findByUsername("test1");
		verify(userRepository, never()).save(user);

	}

	@Test
	public void getUserByUsernameTest() throws UserNameNotFoundException {
		UserEntity user = new UserEntity(1, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", true);
		Mockito.when(userRepository.findByUsername("test1")).thenReturn(user);
		UserEntity userByUsername = userService.getUserByUsername("test1");
		assertThat(userByUsername).isNotNull();
		verify(userRepository).findByUsername("test1");
	}

	@Test
	public void shouldThrowErrorWhengetUserByUsernameTest() {
		Mockito.when(userRepository.findByUsername("test1")).thenReturn(null);
		assertThrows(UserNameNotFoundException.class, () -> userService.getUserByUsername("test1"));
		verify(userRepository).findByUsername("test1");
	}

	@Test
	public void getUserByEmailTest() throws UserNameNotFoundException {
		UserEntity user = new UserEntity(1, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", true);
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		UserEntity userByEmail = userService.getUserByEmail(user.getEmail());
		assertThat(userByEmail).isNotNull();
		verify(userRepository).findByEmail(user.getEmail());
	}

	@Test
	public void getUserByIdTest() {
		UserEntity user = new UserEntity(1L, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", true);
		Mockito.when(userRepository.getById(user.getId())).thenReturn(user);
		UserEntity userById = userService.getUserById(user.getId());
		assertEquals(userById, user);
		verify(userRepository, times(1)).getById(user.getId());
	}

	@Test
	public void confirmUserTest() {
		UserEntity user = new UserEntity(1L, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789", false);
		Mockito.when(userRepository.getById(1L)).thenReturn(user);
		userService.confirmUser(1L);
		assertTrue(user.getConfirmed());
		verify(userRepository, times(1)).getById(1L);
		verify(userRepository).save(user);
	}

	@Test
	public void updateUserTest() {
		UserEntity existingUser = new UserEntity(1L, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789",
				false);
		Mockito.when(userRepository.getById(1L)).thenReturn(existingUser);
		UserEntity newUser = new UserEntity(1L, "test", "test2", "pass", "NORMAL", "test@gmail.com", "123456789",
				false);
		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
		UserEntity updateUser = userService.updateUser(1L, newUser);
		assertThat(updateUser).isNotNull();
		verify(userRepository, times(1)).getById(1L);
		verify(userRepository).save(newUser);
	}

	@Test
	public void shoulThrowErrorWhenUpdateUserTest() {
		Mockito.when(userRepository.getById(1L))
				.thenThrow(new EntityNotFoundException("User with id : " + 1L + " not Found"));
		assertThrows(EntityNotFoundException.class, () -> userService.updateUser(1L, new UserEntity()));
		verify(userRepository, times(1)).getById(1L);
		verify(userRepository, never()).save(new UserEntity());
	}

	@Test
	public void deleteUserTest() {
		userService.deleteUser(1L);
		verify(userRepository).deleteById(1L);
	}

	@Test
	public void shouldThrowErrorWhenDeleteUserTest() {
		doThrow(new EntityNotFoundException("User with id : " + 1L + " not Found")).when(userRepository).deleteById(1L);
		assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(1L));
		verify(userRepository).deleteById(1L);
	}

	@Test
	public void changePasswordTest() {
		UserEntity existingUser = new UserEntity(1L, "test", "test1", "pass", "NORMAL", "test@gmail.com", "123456789",
				false);
		Mockito.when(userRepository.getById(1L)).thenReturn(existingUser);
		Mockito.when(passwordEncoder.encode("newPassword")).thenReturn("newPasswordSet");
		userService.changePassword(1L, "newPassword");
		assertEquals("newPasswordSet", existingUser.getPassword());
		verify(userRepository, times(1)).getById(1L);
		verify(userRepository).save(existingUser);
		verify(passwordEncoder).encode("newPassword");
	}

}
