package forms;

import java.util.Map;

import utilities.DataUtilities;

public abstract class Form {

  protected Map<String, String[]> fields;

  protected boolean checkData() {
    // Check over each of the fields.
    for (String str : fields.keySet()) {
      String[] value = fields.get(str);
      if (!DataUtilities.isValid(value)) {
        return false;
      }
    }
    return true;
  }

}
