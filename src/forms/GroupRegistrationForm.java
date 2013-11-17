package forms;

import java.util.Set;

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

  public String getCreateGroupJSONRequest() {
    StringBuilder strB = new StringBuilder();

    String[] possibleFields = { "name", "officeID", "active", "activationDate",
        "externalId", "staffId", "clientMembers" };

    // set dateformat
    strB.append("\t\"dateFormat\": \"dd\\MM\\yyyy\"");
    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        strB.append("\t\"" + str + "\": " + fields.get(str)[0] + ",\n");
      }
    }
    strB.append("}\n");
    return strB.toString();
  }

}
