package org.uoa.axu732;

import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// Import the AppTest class

public class App {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(org.uoa.axu732.AppTest.class);

    if (result.wasSuccessful()) {
      System.out.println("All tests passed successfully.");
    } else {
      System.out.println("Some tests failed.");
      System.out.println("Result get failure" + result.getFailures());
      List<Failure> failures = result.getFailures();

      for (Failure failure : failures) {
        System.out.println("failure get class" + failure.getClass());
        System.out.println("failure get message" + failure.getMessage());
      }
    }
  }
}
