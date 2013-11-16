package loginManager;

public class PasswordMismatchError extends Exception{
	public PasswordMismatchError(String msg) {
		System.out.println(msg);
	}
}
