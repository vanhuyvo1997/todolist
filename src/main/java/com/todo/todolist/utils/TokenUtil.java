package com.todo.todolist.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.todo.todolist.models.User;

import io.jsonwebtoken.Jwts;

public class TokenUtil {

	private static final Duration ACCESS_TOKEN_TIME_LIVE = Duration.ofMillis(1000 * 60 * 60 * 24 * 1);
	private static final Duration REFRESH_TOKEN_TIME_LIVE = Duration.ofMillis(1000 * 60 * 60 * 24 * 1);

	public static String generateAccessToken(User user) {
		Instant now = Instant.now();

		Map<String, Object> customClaims = new HashMap<>();
		customClaims.put("type", "access_token");
		customClaims.put("avatar", user.getAvatar());
		customClaims.put("first_name", user.getFirstName());
		customClaims.put("last_name", user.getLastName());
		customClaims.put("authorities",
				user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));

		String jws = Jwts.builder()
				.id(user.getId())
				.subject(user.getEmail())
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(ACCESS_TOKEN_TIME_LIVE)))
				.claims(customClaims)
				.signWith(JwtKeyHolder.getPrivateKey())
				.compact();
		return jws;
	}

	public static String generateRefreshToken(User user) {
		var now = Instant.now();
		String jws = Jwts.builder()
				.id(user.getId())
				.subject(user.getEmail())
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(REFRESH_TOKEN_TIME_LIVE)))
				.claim("type", "refresh_token")
				.signWith(JwtKeyHolder.getPrivateKey())
				.compact();
		return jws;
	}

}
