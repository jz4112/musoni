package com.example.musoni;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
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
	static String hashAlg = "SHA-256";
	
	public static String hash(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(hashAlg);
		return new String(digest.digest(password.getBytes("UTF-8")));
		
	}
	
	public static byte[] generateKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
		byte[] keyStart = password.getBytes("UTF-8");
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		SecureRandom scr = SecureRandom.getInstance("SHA1PRNG");
		scr.setSeed(keyStart);
		keyGen.init(128,scr);
		SecretKey sKey = keyGen.generateKey();
		return sKey.getEncoded();
	}
	
	public static byte[] encrypt(byte[] key, byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec sKeySpec = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
		return cipher.doFinal(data);
	}
	
	public static String decrypt(byte[] key, byte[] encrypted) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec sKeySpec = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
		return new String(cipher.doFinal(encrypted));
	}

}
