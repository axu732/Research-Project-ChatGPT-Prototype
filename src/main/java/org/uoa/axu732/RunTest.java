package org.uoa.axu732;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTest {
  public void main(String[] args) {
    Result result = JUnitCore.runClasses(org.uoa.axu732.AppTest.class);

    if (result.wasSuccessful()) {
      System.out.println("All tests passed successfully.");
    } else {
      System.out.println("Some tests failed.");
      System.out.println("Result get failure" + result.getFailures());
      List<Failure> failures = result.getFailures();

      for (Failure failure : failures) {
        System.out.println("failure get class: " + failure.getClass());
        System.out.println("failure get message: " + failure.getMessage());
        System.out.println("failure get description " + failure.getDescription().toString());
      }
    }
  }

  public void runTest() {
    ChatGPT GPT = new ChatGPT();
    Result result = JUnitCore.runClasses(org.uoa.axu732.AppTest.class);

    if (result.wasSuccessful()) {
      System.out.println("All tests passed successfully.");
    } else {
      Failure failure = result.getFailures().get(0);

      GPT.sendFirstMessage(failure.getMessage(), getTestCode());
      GPT.sendSecondMessage(getClientCode());
    }
  }

  private String getTestCode() {
    try {
      Path testFilePath = Paths.get("src/test/java/AppTest.java");
      FileInputStream testFileInputStream = new FileInputStream(testFilePath.toFile());
      StringBuilder testCode = new StringBuilder();

      ParseResult<CompilationUnit> testParseResult = new JavaParser().parse(testFileInputStream);
      if (testParseResult.isSuccessful()) {
        CompilationUnit testCu = testParseResult.getResult().get();

        testCu
            .findAll(MethodDeclaration.class)
            .forEach(
                method -> {
                  if (method.getNameAsString().equals("testCreateVariableName")) {
                    testCode.append(method.getDeclarationAsString());
                    testCode.append(method.getBody().orElse(null));
                  }
                });
        return testCode.toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getClientCode() {
    try {
      Path demoFilePath = Paths.get("src/main/java/org/uoa/axu732/test_data/Demo.java");
      FileInputStream demoFileInputStream = new FileInputStream(demoFilePath.toFile());
      StringBuilder clientCode = new StringBuilder();

      ParseResult<CompilationUnit> demoParseResult = new JavaParser().parse(demoFileInputStream);
      if (demoParseResult.isSuccessful()) {
        CompilationUnit demoCu = demoParseResult.getResult().get();

        demoCu
            .findAll(MethodDeclaration.class)
            .forEach(
                method -> {
                  if (method.getNameAsString().equals("createVariableName")) {
                    clientCode.append(method.getDeclarationAsString());
                    clientCode.append(method.getBody().orElse(null));
                  }
                });
        return clientCode.toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
