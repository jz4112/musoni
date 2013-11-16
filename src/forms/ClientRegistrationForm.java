package forms;

import java.util.Set;


public class ClientRegistrationForm extends Form {

  public boolean validateNulls() {
    Set<String> keySet = fields.keySet();

    if(!keySet.contains("officeId")) {
      // We need office ID
      return false;
    }

    if((!keySet.contains("firstname") || !keySet.contains("lastname"))
        && !keySet.contains("fullname")) {
      return false;
    }

    fields.put("active", "false");

    return true;
  }

  public String getCreateClientJSONRequest() {
    StringBuilder strB = new StringBuilder();
    strB.append("POST clients\n");
    strB.append("Content-Type: application/json Request Body:");
    strB.append("{\n");
    for(String str : fields.keySet()) {
      strB.append("\t\"" + str + "\": " + fields.get(str) + ",\n");
    }
    strB.append("}\n");
    return null;
  }

}
