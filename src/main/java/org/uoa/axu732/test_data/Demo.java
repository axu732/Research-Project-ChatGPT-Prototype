package org.uoa.axu732.test_data;

import org.util.StringFormatter;

public class Demo {
  public static void main(String[] args) {
    String str1 = "first";
    String str2 = "name";
    System.out.println(createVariableName(str1, str2));
  }

  public static String createVariableName(String str1, String str2) {
    return StringFormatter.appendValues(str1, str2);
  }
}
