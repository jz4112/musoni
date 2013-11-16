package com.example.code4good;

public class UsernameMismatchError extends Exception {
	public UsernameMismatchError(String username) {
		System.out.println(username + " not found in the data base");
	}
}
