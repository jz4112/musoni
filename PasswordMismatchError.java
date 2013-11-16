package com.example.code4good;

public class PasswordMismatchError extends Exception{
	public PasswordMismatchError(String msg) {
		System.out.println(msg);
	}
}
