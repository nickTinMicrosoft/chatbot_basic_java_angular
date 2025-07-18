package com.example.azureopenaiapi.agent;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;

public class ChatAgent implements Agent {
    @org.springframework.beans.factory.annotation.Value("${azure.openai.api.key}")
    private String openAiApiKey;
    @org.springframework.beans.factory.annotation.Value("${azure.openai.endpoint}")
    private String openAiEndpoint;
    @org.springframework.beans.factory.annotation.Value("${azure.openai.deployment}")
    private String openAiDeployment;
    private OpenAIClient openAIClient;

    public ChatAgent() {
        // Initialize OpenAIClient using OpenAIClientBuilder
        this.openAIClient = new com.azure.ai.openai.OpenAIClientBuilder()
            .endpoint(openAiEndpoint)
            .credential(new com.azure.core.credential.AzureKeyCredential(openAiApiKey))
            .buildClient();
    }

    @Override
    public String process(String userInput) {
        // Call Azure OpenAI LLM and return response
        try {
            java.util.List<ChatMessage> messages = java.util.List.of(
                new ChatMessage(ChatRole.USER).setContent(userInput)
            );
            ChatCompletionsOptions options = new ChatCompletionsOptions(messages);
            ChatCompletions completions = openAIClient.getChatCompletions(openAiDeployment, options);
            if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
                return completions.getChoices().get(0).getMessage().getContent();
            } else {
                return "No response from LLM.";
            }
        } catch (Exception e) {
            return "Error calling Azure OpenAI: " + e.getMessage();
        }
    }
}
