package org.uoa.axu732;

import org.uoa.axu732.test_data.Demo;

public class App {
  public static void main(String[] args) {
    ChatGPT chatGPT = new ChatGPT();
    chatGPT.sendMessage();

    System.out.println(Demo.createVariableName("Guy", "Goon"));
  }
}
