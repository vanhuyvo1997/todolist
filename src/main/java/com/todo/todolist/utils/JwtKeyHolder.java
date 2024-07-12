package com.todo.todolist.utils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtKeyHolder {
	private static KeyPair keyPair;
	
	public static void setKeyPair(KeyPair pair) {
		keyPair = pair;
	}
	
	public static PrivateKey getPrivateKey() {
		return keyPair.getPrivate();
	}
	
	public static PublicKey getPublicKey() {
		return keyPair.getPublic();
	}
	
	public static KeyPair getKeyPair() {
		return keyPair;
	}
	

}
