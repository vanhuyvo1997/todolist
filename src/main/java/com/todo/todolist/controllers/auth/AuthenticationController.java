package com.todo.todolist.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todolist.dtos.LoginDto;
import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.services.AuthenticationService;
import com.todo.todolist.services.UserService;

import jakarta.validation.Valid;

@RequestMapping("api/authentication")
@RestController
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@Autowired private AuthenticationService authService;
	
	@PostMapping("register")
	public ResponseEntity<?> register(@Valid @RequestBody CreateUserDto createUserDto) {
		return ResponseEntity.ok(userService.create(createUserDto));
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginData){
		// check email
		// check password
		// neu hop le thi tra ve refresh và access token
		// neu không hop le thi quang ngoai le, sao cho tạo thành mã 403;
		return ResponseEntity.ok(authService.login(loginData));
	}
}
