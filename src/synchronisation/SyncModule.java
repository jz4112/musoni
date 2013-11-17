package synchronisation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import localStore.LocalStore;
import loginManager.CertificateValidation;

import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import forms.ClientRegistrationForm;
import forms.Form;
import forms.GroupRegistrationForm;
import forms.LoanApplicationForm;

public class SyncModule {

  static Context ctx = LocalStore.getInstance().getContext();
  private static String tenantIdentifier = "code4good";

  public static boolean WifiConnected() {
    ConnectivityManager connManager = (ConnectivityManager) ctx
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mWifi = connManager
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    return mWifi.isConnected();
  }

  public static boolean formsToSync() {
    LocalStore store = LocalStore.getInstance();
    List<Form> forms = store.getForms();
    for (Form f : forms) {
      if (f.toBeSynced()) {
        return true;
      }
    }
    return false;
  }

  public static void uploadForm(Form form) throws Exception {
    if (!form.toBeSynced()) {
      return;
    }
    if (form instanceof ClientRegistrationForm) {
      ClientRegistrationForm crf = (ClientRegistrationForm) form;
      String response = sendMessageToServer(
          crf.buildCreateClientQuery(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/clients?tenantIdentifier="
              + tenantIdentifier);

      JSONObject obj = new JSONObject(response);
      String clientID = (String) obj.get("clientID");
      crf.putClientID(clientID);

      sendMessageToServer(
          crf.buildClientBusinessAddition(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/datatables/ml_client_business/"
              + clientID + "?tenantIdentifier=" + tenantIdentifier);
      sendMessageToServer(
          crf.buildClientNOKAddition(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/datatables/ml_client_next_of_kin/"
              + clientID + "?tenantIdentifier=" + tenantIdentifier);
      sendMessageToServer(
          crf.buildClientDetailsAddition(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/datatables/ml_client_details/"
              + clientID + "?tenantIdentifier=" + tenantIdentifier);
    } else if (form instanceof GroupRegistrationForm) {
      GroupRegistrationForm grf = (GroupRegistrationForm) form;
      String response = sendMessageToServer(
          grf.getCreateGroupJSONRequest(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/groups?tenantIdentifier="
              + tenantIdentifier);

      JSONObject obj = new JSONObject(response);
      String groupID = (String) obj.get("groupID");
      grf.putGroupID(groupID);

      sendMessageToServer(
          grf.getMLGroupDetailsJSONRequest(),
          "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/datatables/ml_group_details/"
              + groupID + "?tenantIdentifier=" + tenantIdentifier);
    } else if (form instanceof LoanApplicationForm) {
      // TODO!
    }
  }

  private static String sendMessageToServer(JSONObject obj, String url)
      throws Exception {
    String message = obj.toString();
    CertificateValidation.invalidate();
    URL createClientURL = new URL(url);
    HttpsURLConnection con = (HttpsURLConnection) createClientURL
        .openConnection();
    con.setRequestMethod("POST");

    con.setDoInput(true);
    con.setDoOutput(true);

    // Send request
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeUTF(message);
    wr.flush();
    wr.close();

    // Get Response
    InputStream is = con.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    String line;
    StringBuffer response = new StringBuffer();
    while ((line = rd.readLine()) != null) {
      response.append(line);
      response.append('\r');
    }
    rd.close();
    return response.toString();
  }

  public static void uploadAllForms() throws Exception {
    LocalStore store = LocalStore.getInstance();
    List<Form> list = store.getForms();

    for (Form form : list) {
      uploadForm(form);
    }
  }

}
