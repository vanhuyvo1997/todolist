package com.todo.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.todolist.dtos.LoginDto;
import com.todo.todolist.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {
	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	public Object login(LoginDto loginData) {
		var user = userRepository.findByEmail(loginData.email());
		if (user.isEmpty() || !passwordEncoder.matches(loginData.password(), user.get().getPassword()))
			throw new UsernameNotFoundException("Not found user with given username and passowrd");
		return null;
	}

}
