package forms;

import java.io.Serializable;
import java.util.Map;

import utilities.DataUtilities;

public abstract class Form implements Serializable {

  protected Map<String, String[]> fields;

  protected boolean toBeSynced;

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

  public boolean toBeSynced() {
    return toBeSynced;
  }

}
