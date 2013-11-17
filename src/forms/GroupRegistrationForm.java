package forms;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class GroupRegistrationForm extends Form {

  private static final long serialVersionUID = 1L;

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
