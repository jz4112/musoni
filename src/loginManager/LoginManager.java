package loginManager;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;


import encryption.Encryption;

public class LoginManager {
	private String username;
	private String password;

	private static String hashedPassword;
	private static String sessionID;

	public LoginManager(String username, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		this.username = username;
		this.password = password;
		hashedPassword = Encryption.hash(password); // STORE THE HASHED VALUE IN
													// THE DATABASE
	}

	// if the login does not have records in database, must have internet
	// access, check
	// if it's valid login and add to local database

	public boolean login() throws Exception {

		if (LoginDataBase.getInstance().constainsUsername(username)) {
			// local memory has records of that
			if (hashedPassword == LoginDataBase.getInstance()
					.getHashedPassword(password)) {
				sessionID = UUID.randomUUID().toString();
				return true;
			} else {
				PassExceptionToUI.passToUI(new PasswordMismatchError());
				return false;
			}
		} else {
			// check with web server
			CertificateValidation.invalidate();
			URL url = new URL(
					"https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/authentication?username="
							+ username
							+ "&password="
							+ password
							+ "&tenantIdentifier=code4good");

			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// if the account is found in web server
				LoginDataBase.getInstance().updateDataBase(username, hashedPassword);
				return true;
			} else {
				// if web server does not have match
				PassExceptionToUI.passToUI(new UsernameMismatchError(username));
				return false;
			}
		}
	}

	public void setPassword(String password)
			throws Exception {
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
