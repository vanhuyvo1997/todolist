package com.todo.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.dtos.user.UserDto;
import com.todo.todolist.exceptions.ResourceAlreadyExistedException;
import com.todo.todolist.mappers.UserMapper;
import com.todo.todolist.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;

	public List<UserDto> getAll() {
		return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
	}

	public UserDto create(CreateUserDto createUserDto) {
		if (userRepository.findByEmail(createUserDto.email()).isPresent()) {
			throw new ResourceAlreadyExistedException(createUserDto.email() + "was already in use");
		} else {
			var user = userMapper.toUser(createUserDto);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAvatar("default-avatar.png");
			return userMapper.toUserDto(userRepository.save(user));
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var optUser = userRepository.findByEmail(username);
		if(optUser.isPresent()) return optUser.get();
		throw new UsernameNotFoundException(username + " is not found.");
	}
}
