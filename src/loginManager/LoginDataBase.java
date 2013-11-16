package com.example.code4good;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * login data base that stores large login details in hard disk on the phone
 * <username, password>
 */
public class LoginDataBase {
	// map stores user name and hashed password
	private static Map<String, String> map;
	private static File dir;
	private LoginDataBase() {
		map = new HashMap<String, String>();
		dir = new File("." + File.separator + "loginDataBase");
	}
	public static LoginDataBase getInstance() {
		return new LoginDataBase();
	}
	public boolean constainsUsername(String user) {
		return map.containsKey(user);
	}
	public String getHashedPassword(String password) {
		return Encryption.hash(password);
	}
	public void changePassword(String username, String newPassword) {
		//one can only change his/her password after logged in successfully
		map.put(username, Encryption.hash(newPassword));
	}
	
	
	
}
