package loginManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import localStore.LocalStore;
import android.content.Context;
import encryption.Encryption;

/**
 * login data base that stores large login details in hard disk on the phone an
 * user name must log in with internet access for the first time in order to
 * store its login details in local repository <username, password>
 *
 * every time an user open the app, the login details file is loading into map
 * which has <username, hashedPassword> as its value. Everytime a new user is
 * detected, we add its login detail into the file
 */
public class LoginDataBase {
	// map stores user name and hashed password
	private static Map<String, String> map = new HashMap<String, String>();
	private static String fileName = "loginDataBase.txt";
	private static LoginDataBase instance = null;
	private static Context context = LocalStore.getInstance().getContext();
	private static FileInputStream fis;

	static {
		fileName = "loginDataBase.txt";
		try {
			fis = context.openFileInput(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// load file into the map
		int b = -1;
		StringBuilder sb = new StringBuilder();
		String name = null;
		String password = null;
		try {
			while ((b = fis.read()) != -1) {
				if (b != ',') {
					sb.append((char) b);
				} else {
					name = sb.toString();
					break;
				}
			}
			sb.delete(0, sb.length());
			while ((b = fis.read()) != -1) {
					sb.append((char) b);
			}
			password = sb.toString();
			map.put(name, password);
		} catch (IOException e) {
			PassExceptionToUI.passToUI(e);
		}
	}
	private LoginDataBase() throws FileNotFoundException {
		fis = context.openFileInput(fileName);

	}
	public static LoginDataBase getInstance() throws FileNotFoundException {
		if (instance == null) {
			instance = new LoginDataBase();
		}
		return instance;
	}

	public boolean constainsUsername(String user) {
		return map.containsKey(user);
	}

	public String getHashedPassword(String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return Encryption.hash(password);
	}

	public void changePassword(String username, String newPassword)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// one can only change his/her password after logged in successfully
		map.put(username, Encryption.hash(newPassword));
	}

	public void updateDataBase(String username, String password)
			throws NoSuchAlgorithmException, IOException {
		map.put(username, Encryption.hash(password));
		FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
		String data = username + "," + password + "\n";
		fos.write(data.getBytes());
		fos.close();
	}

}
