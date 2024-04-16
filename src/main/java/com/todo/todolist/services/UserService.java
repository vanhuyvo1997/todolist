package com.todo.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.dtos.user.UserDto;
import com.todo.todolist.exceptions.ResourceAlreadyExistedException;
import com.todo.todolist.mappers.UserMapper;
import com.todo.todolist.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final UserMapper userMapper;

	public List<UserDto> getAll() {
		return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
	}

	public UserDto create(CreateUserDto createUserDto) {
		if (userRepository.findByEmail(createUserDto.email()).isPresent()) {
			throw new ResourceAlreadyExistedException("User Already existed");
		} else {
			var user = userMapper.toUser(createUserDto);
			user.setAvatar("default-avatar.png");
			return userMapper.toUserDto(userRepository.save(user));
		}
	}
}
