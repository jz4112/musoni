package loginManager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import encryption.Encryption;

public class LoginManager {
	private String username;
	private String password;

	private static String hashedPassword;


	public LoginManager(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
