package forms;

import java.util.Set;

public class GroupRegistrationForm extends Form {

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
    strB.append("POST groups\n");
    strB.append("Content-Type: application/json Request Body:");
    strB.append("{\n");
    for (String str : fields.keySet()) {
      strB.append("\t\"" + str + "\": " + fields.get(str)[0] + ",\n");
    }
    strB.append("}\n");
    return strB.toString();
  }

}
