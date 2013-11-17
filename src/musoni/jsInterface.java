package com.example.musoni;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import loginManager.LoginManager;
import synchronisation.SyncModule;
import android.content.Context;
import android.webkit.JavascriptInterface;

public class jsInterface {

	private Context ctx;
	private int attemptLeft;
	private MainActivity currAct;
	
	public jsInterface(Context ctx, MainActivity act) {
		this.ctx = ctx;
		attemptLeft = 5;
		currAct = act;
	}
	
	@JavascriptInterface
	public String attemptLogIn(String username, String password) throws Exception {
		LoginManager login = new LoginManager(username, password);
		if (login.login()) {
			//attemptLeft = 5;
			return "";
		}
	//	attemptLeft--;
		return "Log in failed! Username and password not recognised.";
	}
	
	@JavascriptInterface
	public void navigateToMain() {
		currAct.moveTo("main.html");
	}
	
	@JavascriptInterface
	public void navigateToRegistration() {
		currAct.moveTo("registration.html");
	}
	
	@JavascriptInterface
	public void navigateToBusiness() {
		currAct.moveTo("business.html");
	}
	
	@JavascriptInterface
	public void navigateToLoan() {
		currAct.moveTo("loan.html");
	}
	
	@JavascriptInterface
	public void navigateToClientRegistration() {
		currAct.moveTo("clientregistration.html");
	}
	
	@JavascriptInterface
	public void navigateToGroupRegistration() {
		currAct.moveTo("groupregistration.html");
	}
	
	@JavascriptInterface
	public boolean isSyncPending() {
		return SyncModule.formsToSync();
	}
	
	@JavascriptInterface
	public String getCurrentUsername() {
		return "fuck it";
	}
	
	@JavascriptInterface
	public boolean canAnimate() {
		return true;
	}
	
}
