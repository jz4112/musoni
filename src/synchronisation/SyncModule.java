package synchronisation;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import forms.Form;

public class SyncModule {

  public static boolean WifiConnected() {
    ConnectivityManager connManager = (ConnectivityManager) (new Activity()).getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    return mWifi.isConnected();
  }

  public static void uploadForm(Form f) {
    // TODO
  }

  public static void uploadAllForms() {
    // TODO
  }

}
