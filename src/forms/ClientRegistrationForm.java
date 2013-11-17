package forms;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class ClientRegistrationForm extends Form {

  private static final long serialVersionUID = 1L;

  public void putClientID(String id) {
    String[] str = { id, "" };
    fields.put("client_id", str);
  }

  public boolean validateData() {
    Set<String> keySet = fields.keySet();

    if (!keySet.contains("officeId")) {
      // We need office ID - ?
      return false;
    }

    if ((!keySet.contains("firstname") || !keySet.contains("lastname"))
        && !keySet.contains("fullname")) {
      // Must have a firstname and lastname, OR a fullname (for businesses)
      return false;
    }

    return checkData();
  }

  public JSONObject buildClientBusinessAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "client_id", "businessName", "businessType",
        "businessStartDate", "address", "city", "country", "postalCode" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

  public JSONObject buildClientDetailsAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "client_id", "town", "address", "phoneNumber",
        "gender_cd", "maritalStatus", "dateOfBirth", "state" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

  public JSONObject buildClientNOKAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "nok_id", "nok_client_id", "nok_name",
        "nok_dateOfBirth", "nok_address", "nok_city", "nok_phoneNumber",
        "nok_relation_to_client", "nok_state" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

  public JSONObject buildCreateClientQuery() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "firstname", "lastname", "fullname",
        "officeId", "active", "activationDate", "groupId", "externalId",
        "accountNo", "staffid" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

}