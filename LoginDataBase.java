package loginManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

import com.example.code4good.PassExceptionToUI;

import encryption.Encryption;

/**
 * login data base that stores large login details in hard disk on the phone an
 * user name must log in with internet access for the first time in order to
 * store its login details in local repository <username, password>
 * 
 * every time an user open the app, the login details file is loading into map
 * which has <username, hashedPassword> as its value.
 * Everytime a new user is detected, we add its login detail into the file
 */
public class LoginDataBase {
	// map stores user name and hashed password
	static Map<String, String> map = new HashMap<String, String>();
	private static File file;
	private static LoginDataBase instance = null;
	private static RandomAccessFile raf;
	
	static {
		String str = Environment.getExternalStorageDirectory() + File.separator + "loginDataBase.txt";
		file = new File(str);
		try {
			if (! file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//file = new File("android_asset/access.txt");
		//AssetManager assetManager = 
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			//System.exit(0);
		}		
		
		//load file into the map
		String loginDetail;
		try {
			loginDetail = raf.readLine();
		} catch (IOException e1) {
			loginDetail = null;
			e1.printStackTrace();
		}
		try {
			while (loginDetail != null) {
				System.exit(0);
				String[] login = loginDetail.split(",");
				String username = login[0];
				String password = login[1];
				map.put(username, password);
				loginDetail = raf.readLine();
			}
		} catch (IOException e) {
			PassExceptionToUI.passToUI(e);
		}
		try {
			map.put("wong", Encryption.hash("kuo"));
			map.put("testuser", Encryption.hash("awesome"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		map.put(username, Encryption.hash(password));
		try {
			raf.writeChars(username + "," + map.get(username));
		} catch (IOException e) {
	//		PassExceptionToUI.passToUI(e);
		}
	}

}
