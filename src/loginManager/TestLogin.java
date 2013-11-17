package loginManager;

import java.io.File;
import java.io.FileOutputStream;

import localStore.LocalStore;
import android.content.Context;
import encryption.Encryption;

public class TestLogin {
	public static void main(String[] args) throws Exception {
		Context cnt = LocalStore.getInstance().getContext();
		File file = new File("." + File.separator + "file.txt");
		file.createNewFile();
		FileOutputStream fos = cnt.openFileOutput("file.txt", Context.MODE_APPEND);
		String data = "aaa,aaa";
		fos.write(data.getBytes());
		fos.flush();
		fos.close();
		LoginManager username = new LoginManager("aaa" , Encryption.hash("aaa"));
		username.login();
		System.out.println("u"+username.login());
	}
}
