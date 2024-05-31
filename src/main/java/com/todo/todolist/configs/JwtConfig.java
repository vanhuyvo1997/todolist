package com.todo.todolist.configs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.todo.todolist.utils.JwtKeyHolder;

import jakarta.annotation.PostConstruct;

@Configuration
public class JwtConfig {

	@Value("${jwt.private-key-file}")
	private Resource privateKeyResource;

	@Value("${jwt.public-key-file}")
	private Resource publicKeyResource;

	private static PrivateKey privateKey;
	private static PublicKey publicKey;

	@PostConstruct
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		privateKey = loadPrivateKey(privateKeyResource);
		publicKey = loadPublicKey(publicKeyResource);
		JwtKeyHolder.setKeyPair(new KeyPair(publicKey, privateKey));
	}

	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public static PublicKey getPublicKey() {
		return publicKey;
	}

	private PublicKey loadPublicKey(Resource publicKeyResource2)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String publicKeyPEM = new String(FileCopyUtils.copyToByteArray(publicKeyResource.getInputStream()),
				StandardCharsets.UTF_8);
		publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "")
				.replaceAll("\\s", "");
		byte[] decodedKey = Base64.getDecoder().decode(publicKeyPEM);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		return keyFactory.generatePublic(keySpec);
	}

	private PrivateKey loadPrivateKey(Resource privateKeyResource2)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String publicKeyPEM = new String(FileCopyUtils.copyToByteArray(privateKeyResource.getInputStream()),
				StandardCharsets.UTF_8);
		publicKeyPEM = publicKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "")
				.replaceAll("\\s", "");
		byte[] decodedKey = Base64.getDecoder().decode(publicKeyPEM);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		return keyFactory.generatePrivate(keySpec);
	}

}