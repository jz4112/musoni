package com.example.code4good;

import java.util.Random;
import com.example.code4good.LoginDataBase;

public class LoginManager {
	private String username;
	private String password;
	
	private static String hashedPassword;


	public LoginManager(String username, String password) {
		this.username = username;
		this.password = password;
		hashedPassword =  Encryption.hash(password); // STORE THE HASHED VALUE IN THE DATABASE
	}

	public boolean checkPasswordValid() throws PasswordMismatchError {
		if (LoginDataBase.constainsUsername(username)) {
			return hashedPassword == LoginDataBase.getInstance().get(password);
		} else {
			throw new PasswordMismatchError("password not found in the data base");
		}
	}

	public void logout() {
		
	}


}
