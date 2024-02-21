package org.uoa.axu732;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ChatGPT {
  private List<ChatMessage> messages = new ArrayList<>();
  private OpenAiService service;

  public ChatGPT() {
    String token = "";
    this.service = new OpenAiService(token);

    ChatMessage systemMessage =
        new ChatMessage(
            ChatMessageRole.SYSTEM.value(),
            "You are tasked with adapting client code for breaking changes.");
    messages.add(systemMessage);
  }

  public void sendMessage() {
    System.out.print("Query: ");
    Scanner scanner = new Scanner(System.in);
    ChatMessage Msg = new ChatMessage(ChatMessageRole.USER.value(), scanner.nextLine());
    messages.add(Msg);

    generateResponse();
    scanner.close();
  }

  public void sendFirstMessage(String testExpectation, String testFailureMessage, String testCode) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the previous version: ");
    String previousVersion = scanner.nextLine();

    System.out.print("Enter the current version: ");
    String currentVersion = scanner.nextLine();

    System.out.print("Enter the library name that was updated: ");
    String libraryName = scanner.nextLine();

    StringBuilder sb = new StringBuilder();
    sb.append(
        "I have a test failing after updating "
            + libraryName
            + " from "
            + previousVersion
            + " to "
            + currentVersion
            + ". ");
    sb.append(
        "It expected "
            + testExpectation
            + " but failed with the message: "
            + testFailureMessage
            + ". ");
    sb.append("This is the test code: " + testCode + ". ");
    sb.append("Can you identify the issue between the expected and actual behavior?");
    ChatMessage Msg = new ChatMessage(ChatMessageRole.USER.value(), sb.toString());
    messages.add(Msg);
  }

  public void sendSecondMessage(String clientCode) {
    StringBuilder sb = new StringBuilder();
    sb.append("Can you adapt this code to fix the issue you found? " + clientCode);
    ChatMessage Msg = new ChatMessage(ChatMessageRole.USER.value(), sb.toString());
    messages.add(Msg);

    generateResponse();
  }

  private void generateResponse() {
    ChatCompletionRequest chatCompletionRequest =
        ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo-0613")
            .messages(messages)
            .temperature(0.2)
            .topP(0.5)
            .n(1)
            .maxTokens(100)
            .logitBias(new HashMap<>())
            .build();

    ChatMessage responseMessage =
        service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

    messages.add(responseMessage); // Add message to chat history

    System.out.println("Response: " + responseMessage.getContent());
  }
}
