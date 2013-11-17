package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DataUtilities {

  private final static String dateFormat = "dd/MM/yyyy";
  private final static List<String> genders = new LinkedList<String>(
      Arrays.asList("Male", "Female"));
  private final static List<String> maritalStatus = new LinkedList<String>(
      Arrays.asList("Single", "Married", "Divorced"));
  private final static List<String> relationships = new LinkedList<String>(
      Arrays.asList("Parent", "Sibling", "Spouse", "Business Associate",
          "Other"));

  // Age threshold for fields that need minimum age validation.
  private final static int AGE_THRESHOLD = 18;

  // Checks if a string array of length 2 [value, type] is well formed.
  public static boolean isValid(String[] str) {
    if (str.length != 2) {
      // something wrong with input
      throw new RuntimeException(
          "Value to be checked should be an array of length 2");
    }

    // array length is 2
    String value = str[0];
    String validationType = str[1];

    // Perform validation as necessary
    if (validationType.equals("email")) {
      return isValidEmailAddress(value);
    } else if (validationType.equals("integer")) {
      try {
        Integer.parseInt(value);
      } catch (NumberFormatException e) {
        return false;
      }
      return true;
    } else if (validationType.equals("double")) {
      try {
        Double.parseDouble(value);
      } catch (NumberFormatException e) {
        return false;
      }
      return true;
    } else if (validationType.equals("date")) {
      return isValidDate(value);
    } else if (validationType.equals("dateWithMinimum")) {
      return isValidDateMin(value);
    } else if (validationType.equals("gender")) {
      return isValidGender(value);
    } else if (validationType.equals("phone")) {
      return isValidPhoneNumber(value);
    } else if (validationType.equals("notnull")) {
      return !value.trim().isEmpty();
    }

    // Doesn't match any of the known validation parameters.
    return true;
  }

  public static boolean isValidPhoneNumber(String str) {
    // Extensible depending on specific requirements.
    // We can change this to be country dependent as well
    // Currently verifying against UK numbers only.
    return str.matches("^0[0-9]{9,10}$");
  }

  public static boolean isValidDate(String str) {
    if (str == null) {
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

  public static boolean isValidDateMin(String str) {
    if (str == null) {
      return false;
    }

    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    sdf.setLenient(false);

    Date date;

    try {
      date = sdf.parse(str);
    } catch (ParseException e) {
      // Not a valid date
      return false;
    }

    // this is a valid date, now to check the minimum
    Calendar entryTime = getCalendar(date);
    Calendar currTime = Calendar.getInstance();

    int age = currTime.get(Calendar.YEAR) - entryTime.get(Calendar.YEAR);

    // The age may be one less than the difference between years; this adjusts
    // for that.
    if (currTime.get(Calendar.MONTH) < entryTime.get(Calendar.MONTH)) {
      age--;
    } else if (currTime.get(Calendar.MONTH) == entryTime.get(Calendar.MONTH)
        && currTime.get(Calendar.DAY_OF_MONTH) < entryTime
            .get(Calendar.DAY_OF_MONTH)) {
      age--;
    }

    return age >= AGE_THRESHOLD;
  }

  private static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
  }

  // Checks if specified string is a valid email address.
  public static boolean isValidEmailAddress(String str) {
    // Check with regex.
    return str
        .matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
  }

  // Checks if specified string is a valid gender.
  public static boolean isValidGender(String str) {
    return genders.contains(str);
  }

  // Checks if specified string is a valid marital status.
  public static boolean isValidMaritalStatus(String str) {
    return maritalStatus.contains(str);
  }

  public static String getMaritalStatus(String str) {
    for (int i = 0; i < maritalStatus.size(); i++) {
      if (str.equals(maritalStatus.get(i))) {
        return i + "";
      }
    }

    // bad!
    throw new RuntimeException("Invalid marital status");
  }

  // Checks if specified string is a valid relationship.
  public static boolean isValidRelation(String str) {
    return relationships.contains(str);
  }

  public static String getRelation(String str) {
    for (int i = 0; i < relationships.size(); i++) {
      if (str.equals(relationships.get(i))) {
        return i + "";
      }
    }

    // bad!
    throw new RuntimeException("Invalid relationship status");
  }

}