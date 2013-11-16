package loginManager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import encryption.Encryption;

/**
 * login data base that stores large login details in hard disk on the phone
 * <username, password>
 */
public class LoginDataBase {
	// map stores user name and hashed password
	private static Map<String, String> map;

	private LoginDataBase() {
		map = new HashMap<String, String>();
	}
	public static Map<String, String> getInstance() {
		return map;
	}
	public static boolean constainsUsername(String user) {
		return map.containsKey(user);
	}

	public static void changePassword(String username, String newPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//one can only change his/her password after logged in successfully
		map.put(username, Encryption.hash(newPassword));
	}
}
