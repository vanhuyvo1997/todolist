package com.todo.todolist.mappers;

import org.springframework.stereotype.Component;

import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.dtos.user.UserDto;
import com.todo.todolist.models.User;

@Component
public class UserMapper {

	
	public UserDto toUserDto(User user) {
		return UserDto.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build();
	}

	
	public User toUser(CreateUserDto createUserDto) {
		return User.builder().email(createUserDto.email()).firstName(createUserDto.firstName())
				.lastName(createUserDto.lastName()).password(createUserDto.password()).build();
	}

}
