package forms;

import java.util.Map;

import utilities.DataUtilities;

public abstract class Form {

  protected Map<String, String[]> fields;

  // Checks every field is valid
  protected boolean checkData() {
    for (String str : fields.keySet()) {
      String[] value = fields.get(str);
      if (!DataUtilities.isValid(value)) {
        return false;
      }
    }
    return true;
  }

}
