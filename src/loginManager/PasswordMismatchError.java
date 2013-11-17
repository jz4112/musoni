package loginManager;

public class PasswordMismatchError extends Exception{
	public PasswordMismatchError() {
		System.out.println("Password not found in the data base");
	}
}
