package com.todo.todolist.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todolist.dtos.LoginDto;
import com.todo.todolist.dtos.user.CreateUserDto;
import com.todo.todolist.services.AuthenticationService;
import com.todo.todolist.services.UserService;

import jakarta.validation.Valid;

@RequestMapping("api/auth")
@RestController
public class AuthenticationController {

	private final UserService userService;

	private final AuthenticationService authService;
	

	
	public AuthenticationController(UserService userService, AuthenticationService authService,
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.authService = authService;
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@Valid @RequestBody CreateUserDto createUserDto) {
		return ResponseEntity.ok(userService.create(createUserDto));
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginData) {			
		return ResponseEntity.ok(authService.login(loginData));
	}
}
