package synchronisation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class ConnectionReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
      // WIFI connected

      try {
        SyncModule.uploadAllForms();
      } catch (Exception e) {
        // Just don't upload what's left, if necessary.
        // The forms that were safely uploaded will have their states changed successfully.
      }
    }
  }

}
