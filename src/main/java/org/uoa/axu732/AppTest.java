package org.uoa.axu732;

import org.junit.Assert;
import org.junit.Test;
import org.uoa.axu732.test_data.Demo;

public class AppTest {
  @Test
  public void testCreateVariableName() {
    String val1 = "Last";
    String val2 = "Name";
    Assert.assertEquals(val1 + val2, Demo.createVariableName(val1, val2));
  }
}
