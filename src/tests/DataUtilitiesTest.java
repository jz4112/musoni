package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import utilities.DataUtilities;

public class DataUtilitiesTest {

  @Test
  public void isValidFailsIfTooFewArgs() {
    try {
      String[] test = { "Hello!" };
      DataUtilities.isValid(test);
      fail();
    } catch (RuntimeException re) {
      // pass!
    }
  }

  @Test
  public void isValidFailsIfTooManyArgs() {
    try {
      String[] test = { "Hello!", "World!", "Coffee!" };
      DataUtilities.isValid(test);
      fail();
    } catch (RuntimeException re) {
      // pass!
    }
  }

  @Test
  public void recognisesValidDates() {
    String[] test = { "10/10/1990", "date" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "31/12/1888", "date" };
    assertTrue(DataUtilities.isValid(test2));

    // leap year!
    String[] test3 = { "29/2/2060", "date" };
    assertTrue(DataUtilities.isValid(test3));
  }

  @Test
  public void rejectsInvalidDates() {
    // Imaginary month
    String[] test = { "10/13/1990", "date" };
    assertFalse(DataUtilities.isValid(test));

    // April has no 31st
    String[] test2 = { "31/04/1888", "date" };
    assertFalse(DataUtilities.isValid(test2));

    // NOT a leap year
    String[] test3 = { "29/02/2059", "date" };
    assertFalse(DataUtilities.isValid(test3));

    // Rubbish
    String[] test4 = { "abcdefgh", "date" };
    assertFalse(DataUtilities.isValid(test4));

    // Day Zero
    String[] test5 = { "0/02/2059", "date" };
    assertFalse(DataUtilities.isValid(test5));
  }

  @Test
  public void recognisesValidDatesWithThresholds() {
    String[] test = { "10/10/1990", "dateWithMinimum" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "31/12/1888", "dateWithMinimum" };
    assertTrue(DataUtilities.isValid(test2));

    // Edge case - JUST 18 years old
    String[] test3 = { "16/11/1995", "dateWithMinimum" };
    assertTrue(DataUtilities.isValid(test3));
  }

  @Test
  public void rejectsInvalidDatesWithThresholds() {
    // Imaginary month
    String[] test = { "10/13/1990", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test));

    // April has no 31st
    String[] test2 = { "31/04/1888", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test2));

    // NOT a leap year
    String[] test3 = { "29/02/2059", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test3));

    // Rubbish
    String[] test4 = { "abcdefgh", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test4));

    // Day Zero
    String[] test5 = { "0/02/2059", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test5));

    // Date in the future
    String[] test6 = { "29/2/2060", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test6));

    // Too young
    String[] test7 = { "1/1/2013", "dateWithMinimum" };
    assertFalse(DataUtilities.isValid(test7));
  }


  @Test
  public void recognisesValidGenders() {
    String[] test = { "Male", "gender" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "Female", "gender" };
    assertTrue(DataUtilities.isValid(test2));
  }

  @Test
  public void rejectsInvalidGenders() {
    // Empty string
    String[] test = { "", "gender" };
    assertFalse(DataUtilities.isValid(test));

    // Unusual gender
    String[] test2 = { "Flower", "gender" };
    assertFalse(DataUtilities.isValid(test2));
  }

  @Test
  public void recognisesValidPhones() {
    String[] test = { "01234512345", "phone" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "00000000000", "phone" };
    assertTrue(DataUtilities.isValid(test2));
  }

  @Test
  public void rejectsInvalidPhones() {
    // wrong format
    String[] test = { "+447543215555", "phone" };
    assertFalse(DataUtilities.isValid(test));

    // wrong length
    String[] test2 = { "7898789878", "phone" };
    assertFalse(DataUtilities.isValid(test2));

    // unexpected characters
    String[] test3 = { "013579246AB", "phone" };
    assertFalse(DataUtilities.isValid(test3));
  }

  @Test
  public void recognisesValidIntegers() {
    String[] test = { "-1357924680", "integer" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "887766554", "integer" };
    assertTrue(DataUtilities.isValid(test2));

    String[] test3 = { "0", "integer" };
    assertTrue(DataUtilities.isValid(test3));
  }

  @Test
  public void rejectsInvalidIntegers() {
    String[] test = { "5.4", "integer" };
    assertFalse(DataUtilities.isValid(test));

    String[] test2 = { "hello!", "integer" };
    assertFalse(DataUtilities.isValid(test2));

    String[] test3 = { "9E3", "integer" };
    assertFalse(DataUtilities.isValid(test3));
  }

  @Test
  public void recognisesValidDoubles() {
    String[] test = { "-7", "double" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "3.1415926535", "double" };
    assertTrue(DataUtilities.isValid(test2));

    String[] test3 = { "-18.59", "double" };
    assertTrue(DataUtilities.isValid(test3));
  }

  @Test
  public void rejectsInvalidDoubles() {
    String[] test = { "DOUBLE!", "double" };
    assertFalse(DataUtilities.isValid(test));

    String[] test2 = { "1.12.23.34", "double" };
    assertFalse(DataUtilities.isValid(test2));

    String[] test3 = { "987^654", "double" };
    assertFalse(DataUtilities.isValid(test3));
  }

  @Test
  public void recognisesValidNotNull() {
    String[] test = { "-7", "notnull" };
    assertTrue(DataUtilities.isValid(test));

    String[] test2 = { "abcdefghi", "notnull" };
    assertTrue(DataUtilities.isValid(test2));

    String[] test3 = { "!(oOf)*HpNFqD", "notnull" };
    assertTrue(DataUtilities.isValid(test3));
  }

  @Test
  public void rejectsInvalidNotNull() {
    String[] test = { "", "notnull" };
    assertFalse(DataUtilities.isValid(test));

    String[] test2 = { "    ", "notnull" };
    assertFalse(DataUtilities.isValid(test2));
  }

}
