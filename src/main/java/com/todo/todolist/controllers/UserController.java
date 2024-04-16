package com.todo.todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todolist.dtos.user.UserDto;
import com.todo.todolist.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@GetMapping
	public List<UserDto> getAll(){
		return userService.getAll();
	}
	
	

}
