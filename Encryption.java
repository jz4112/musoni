package com.example.musoni;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	
	static String algorithm = "AES";
	
	private static byte[] generateKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
		byte[] keyStart = password.getBytes("UTF-8");
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		SecureRandom scr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
		scr.setSeed(keyStart);
		keyGen.init(128,scr);
		SecretKey sKey = keyGen.generateKey();
		return sKey.getEncoded();
	}
	
	public static byte[] encrypt(String password, byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		byte[] key = generateKey(password);
		SecretKeySpec sKeySpec = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
		return cipher.doFinal(data);
	}
	
	public static byte[] decode(String password, byte[] encrypted) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] key = generateKey(password);
		SecretKeySpec sKeySpec = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
		return cipher.doFinal(encrypted);
	}

}
