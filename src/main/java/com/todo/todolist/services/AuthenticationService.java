package com.todo.todolist.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.todo.todolist.dtos.LoginDto;
import com.todo.todolist.models.User;
import com.todo.todolist.utils.TokenUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {
	private final AuthenticationManager authenticationManager;

	public Object login(LoginDto loginData) {
		UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
				.unauthenticated(loginData.email(), loginData.password());
		var authentication = authenticationManager.authenticate(token);
		
		var user = (User) authentication.getPrincipal();

		var acccessToken = TokenUtil.generateAccessToken(user);
		var refreshToken = TokenUtil.generateRefreshToken(user);	

		Map<String, Object> response = new HashMap<>();
		response.put("accessToken", acccessToken);
		response.put("refreshToken", refreshToken);
 		return response;
	}

}
