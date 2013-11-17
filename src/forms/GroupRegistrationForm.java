package forms;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class GroupRegistrationForm extends Form {

  private static final long serialVersionUID = 1L;

  // Ensures that an officeID and name are present.
  // Also iterates over list of fields and values, ensuring they're well-formed
  public boolean validateData() {
    Set<String> keySet = fields.keySet();

    if (!keySet.contains("officeId")) {
      // We need office ID - ?
      return false;
    }

    if (!keySet.contains("name")) {
      // We need name to be present
      return false;
    }

    return checkData();
  }

  // Builds the JSON message body to be sent to create a group entity in the API.
  public JSONObject getCreateGroupJSONRequest() {
    String[] possibleFields = { "name", "officeID", "active", "activationDate",
        "externalId", "staffId", "clientMembers" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

  // Builds the JSON message body to be sent to create a datatable entry for the
  // group details table.
  public JSONObject getMLGroupDetailsJSONRequest() {
    String[] possibleFields = { "group_id", "registration", "meetingday_cd",
        "meetingfrequency_cd", "meetinglocation" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

  public void putGroupID(String groupID) {
    String[] str = { groupID, "" };
    fields.put("groupID", str);
  }

}
