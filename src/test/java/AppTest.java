
import org.junit.Assert;
import org.junit.Test;
import org.uoa.axu732.test_data.Demo;

public class AppTest {

  /* Test Case to try the demo class's createVariableName method */
  @Test
  public void testCreateVariableName() {
    String val1 = "Last";
    String val2 = "Name";
    Assert.assertEquals(val1 + val2, Demo.createVariableName(val1, val2));
  }
}
