package forms;

import java.io.Serializable;
import java.util.Map;

import utilities.DataUtilities;

public abstract class Form implements Serializable {

  private static final long serialVersionUID = 1L;

  // Map storing strings (field names) to string arrays of length 2 (values and
  // validation types)
  protected Map<String, String[]> fields;

  // Boolean flag indicating if the form needs to be synchronised with the web
  // server.
  protected boolean toBeSynced = true;

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

  // Marks this form as synchronized - i.e. the form does not need to be
  // synchronized with the web server!
  public void markSynced() {
    toBeSynced = false;
  }

}
