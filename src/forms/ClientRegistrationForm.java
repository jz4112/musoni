package forms;

import java.util.Set;

public class ClientRegistrationForm extends Form {

  private static final long serialVersionUID = 1L;

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

  private String buildClientBusinessAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "client_id", "businessName", "businessType",
        "businessStartDate", "address", "city", "country", "postalCode" };

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

  private String buildClientDetailsAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "client_id", "town", "address", "phoneNumber",
        "gender_cd", "maritalStatus", "dateOfBirth", "state" };

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

  private String buildClientNOKAddition() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "nok_id", "nok_client_id", "nok_name", "nok_dateOfBirth",
        "nok_address", "nok_city", "nok_phoneNumber", "nok_relation_to_client", "nok_state" };

    // set dateformat
    strB.append("\t\"dateFormat\": \"dd\\MM\\yyyy\"");
    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        strB.append("\t\"" + str.substring(4) + "\": " + fields.get(str)[0] + ",\n");
      }
    }
    strB.append("}\n");

    return strB.toString();
  }



  private String buildCreateClientQuery() {
    // precondition: well formed form
    StringBuilder strB = new StringBuilder();
    strB.append("{\n");

    String[] possibleFields = { "firstname", "lastname", "fullname",
        "officeId", "active", "activationDate", "groupId", "externalId",
        "accountNo", "staffid" };

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