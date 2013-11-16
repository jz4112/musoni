package forms;

import java.util.Set;

public class ClientRegistrationForm extends Form {

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

    if (checkData()) {
      return true;
    }
    return false;
  }

  public String getCreateClientJSONRequest() {
    StringBuilder strB = new StringBuilder();
    strB.append("POST clients\n");
    strB.append("Content-Type: application/json Request Body:");
    strB.append("{\n");
    for (String str : fields.keySet()) {
      strB.append("\t\"" + str + "\": " + fields.get(str)[0] + ",\n");
    }
    strB.append("}\n");
    return strB.toString();
  }

}
