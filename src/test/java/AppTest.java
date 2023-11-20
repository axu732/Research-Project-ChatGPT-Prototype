import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.uoa.axu732.test_data.Demo;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void testCreateVariableName() {
    String val1 = "Last";
    String val2 = "Name";
    Assert.assertEquals(val1 + val2, Demo.createVariableName(val1, val2));
  }
}
