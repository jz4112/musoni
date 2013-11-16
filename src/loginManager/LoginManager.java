package com.example.code4good;

import java.util.Random;
import java.util.UUID;

import com.example.code4good.LoginDataBase;

public class LoginManager {
	private String username;
	private String password;

	private static String hashedPassword;
	private static String sessionID;
	public LoginManager(String username, String password) {
		this.username = username;
		this.password = password;
		hashedPassword = Encryption.hash(password); // STORE THE HASHED VALUE IN THE DATABASE
	}


	public boolean login() {
		if (LoginDataBase.getInstance().constainsUsername(username)) {
			if (hashedPassword == LoginDataBase.getInstance()
					.getHashedPassword(password)) {
				sessionID = UUID.randomUUID().toString();
				return true;
			} else {
				PassExceptionToUI.passToUI(new PasswordMismatchError());
				return false;
			}
		} else {
			PassExceptionToUI.passToUI(new UsernameMismatchError(username));
			return false;
		}
	}
	public void setPassword(String password) { 
		if (login()) {
			LoginDataBase.getInstance().changePassword(username, password);
		}
	}
	public void logout() {
		sessionID = null;
	}
	public String getSessionID() {
		return sessionID;
	}
}
