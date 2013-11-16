import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataUtilities {

  private final static String dateFormat = "dd/MM/yyyy";
  private final static List<String> genders = new LinkedList<String>(Arrays.asList("Male", "False"));
  private final static List<String> maritalStatus = new LinkedList<String>(Arrays.asList("Single", "Married", "Divorced"));
  private final static List<String> relationships = new LinkedList<String>(Arrays.asList("Parent", "Sibling", "Spouse", "Business Associate", "Other"));

  public static boolean isValidDate(String str) {
    if(str == null) {
      return false;
    }

    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    sdf.setLenient(false);

    try {
      sdf.parse(str);
    } catch (ParseException e) {
      // Not a valid date
      return false;
    }

    // Yes, a valid date
    return true;
  }

  public static boolean isValidGender(String str) {
    return genders.contains(str);
  }

  public static boolean isValidMaritalStatus(String str) {
    return maritalStatus.contains(str);
  }

  public static int getMaritalStatus(String str) {
    for(int i = 0; i < maritalStatus.size(); i++) {
      if(str.equals(maritalStatus.get(i))) {
        return i;
      }
    }

    // bad!
    throw new RuntimeException("Invalid marital status");
  }

  public static boolean isValidRelation(String str) {
    return relationships.contains(str);
  }

  public static int getRelation(String str) {
    for(int i = 0; i < relationships.size(); i++) {
      if(str.equals(relationships.get(i))) {
        return i;
      }
    }

    // bad!
    throw new RuntimeException("Invalid relationship status");
  }

  // For integers, please do Integer.parseInt() ...
  // And catch NumberFormatException and handle appropriately.


}