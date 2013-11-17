package com.example.musoni;

import localStore.LocalStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webview;

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LocalStore.setUp(this);
		webview = new WebView(this);
		webview.addJavascriptInterface(new jsInterface(this, this), "Interact");
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("file:///android_asset/login.html");
		setContentView(webview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void moveTo(String path) {
		webview.loadUrl("file:///android_asset/" + path);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				setContentView(webview);
			}
		});
	}

}
