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

  public void sendConstructedMessage(
      String currentLibraryMethod,
      String previousLibraryMethod,
      String testExpectation,
      String testFailureMessage,
      String clientCode) {
    StringBuilder sb = new StringBuilder();
    sb.append("I have this new Library Method: " + currentLibraryMethod + "\n");
    sb.append("Previously it was: " + previousLibraryMethod + "\n");
    sb.append(
        "Now this test doesn't pass, saying it expects "
            + testExpectation
            + " but it gets "
            + testFailureMessage
            + "\n");
    sb.append(
        "Adapt this client code to account for the changes in the library: " + clientCode + "\n");
    sb.append("Give me only the code");
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
