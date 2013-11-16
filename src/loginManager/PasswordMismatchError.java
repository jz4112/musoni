package com.example.code4good;

public class PasswordMismatchError extends Exception{
	public PasswordMismatchError() {
		System.out.println("Password not found in the data base");
	}
}
