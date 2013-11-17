package synchronisation;

import java.util.List;

import localStore.LocalStore;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import forms.Form;

public class SyncModule {

  public static boolean WifiConnected() {
    ConnectivityManager connManager = (ConnectivityManager) (new Activity())
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mWifi = connManager
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    return mWifi.isConnected();
  }

  public static boolean formsToSync() {
    LocalStore store = LocalStore.getInstance();
    List<Form> forms = store.getForms();
    for(Form f : forms) {
      if(f.toBeSynced()) {
        return true;
      }
    }
    return false;
  }

  public static void uploadForm(Form form) {
    // TODO Create and send JSON requests to create a client and add to the data
    // table.
  }

  public static void uploadAllForms() {
    LocalStore store = LocalStore.getInstance();
    List<Form> list = store.getForms();

    for (Form form : list) {
      uploadForm(form);
    }
  }

}
