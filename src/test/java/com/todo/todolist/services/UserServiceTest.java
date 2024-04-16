package com.todo.todolist.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.dtos.user.UserDto;
import com.todo.todolist.exceptions.ResourceAlreadyExistedException;
import com.todo.todolist.mappers.UserMapper;
import com.todo.todolist.models.Role;
import com.todo.todolist.models.User;
import com.todo.todolist.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {

	}

	@Test
	void testGetAll() {
		// Add some mocked UserDto objects to mockUserList
		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		// Execute
		List<UserDto> result = userService.getAll();

		// Verify
		assertNotNull(result);
		assertEquals(0, result.size());
		verify(userRepository).findAll();
	}

	@Test
	void testCreateNewUser() {
		var mockCreateUserDto = new CreateUserDto("email@gmail.com", "Huy", "Vo Van", "Huy@gmail.com");
		var mockUser = new User("", "Huy@gmail.com", "", "Huy", "Vo Van", Role.USER, "");
		var mockUserDto = new UserDto("", "Huy", "Vo Van", "Huy@gmail.com");

		when(userRepository.findByEmail(mockCreateUserDto.email())).thenReturn(Optional.empty());
		when(userMapper.toUser(mockCreateUserDto)).thenReturn(mockUser);
		when(userMapper.toUserDto(mockUser)).thenReturn(mockUserDto);
		when(userRepository.save(mockUser)).thenReturn(mockUser);

		var result = userService.create(mockCreateUserDto);

		verify(userRepository).findByEmail(mockCreateUserDto.email());
		verify(userMapper).toUser(mockCreateUserDto);
		verify(userRepository).save(mockUser);

		assertEquals("default-avatar.png", mockUser.getAvatar());
		assertEquals(mockUserDto, result);

	}

	@SuppressWarnings("null")
	@Test
	void testCreateExistedUser() {
		var mockCreateUserDto = new CreateUserDto("mock@gmail.com", "Huy", "Vo Van", "1234");
		when(userRepository.findByEmail(mockCreateUserDto.email())).thenReturn(Optional.of(new User()));
		
		assertThrows(ResourceAlreadyExistedException.class, ()->userService.create(mockCreateUserDto), "User Already existed");
		verify(userRepository).findByEmail(mockCreateUserDto.email());
		verify(userMapper, never()).toUser(any());
		verify(userMapper, never()).toUserDto(any());
		verify(userRepository, never()).save(any());
	}

}
