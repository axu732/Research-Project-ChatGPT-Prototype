package org.uoa.axu732.test_data;

import com.google.common.base.MoreObjects;
import org.util.StringFormatter;

public class Demo {

  public static void main(String[] args) {
    String str1 = "first";
    String str2 = "name";
  }

  public static String createVariableName(String str1, String str2) {
    return StringFormatter.appendValues(str1, str2);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("brogy", 23).toString();
  }
}
