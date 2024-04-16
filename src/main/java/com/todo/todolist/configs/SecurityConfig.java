package com.todo.todolist.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain generalFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> {
			authorize
				.requestMatchers("/api/authentication/**").permitAll()
				.requestMatchers("/api", "/api/**").authenticated()
				.requestMatchers("/**").permitAll();
		});
		http.csrf(csrf -> {
			csrf.disable();
		});
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
