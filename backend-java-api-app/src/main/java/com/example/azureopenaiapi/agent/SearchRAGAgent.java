package com.example.azureopenaiapi.agent;

import com.azure.search.documents.SearchClient;
import com.azure.search.documents.models.SearchPagedIterable;

public class SearchRAGAgent implements Agent {
    @org.springframework.beans.factory.annotation.Value("${azure.search.api.key}")
    private String searchApiKey;
    @org.springframework.beans.factory.annotation.Value("${azure.search.endpoint}")
    private String searchEndpoint;
    @org.springframework.beans.factory.annotation.Value("${azure.search.index}")
    private String searchIndex;
    private SearchClient searchClient;

    public SearchRAGAgent() {
        // Initialize SearchClient with endpoint and key
        this.searchClient = new com.azure.search.documents.SearchClientBuilder()
            .endpoint(searchEndpoint)
            .credential(new com.azure.core.credential.AzureKeyCredential(searchApiKey))
            .indexName(searchIndex)
            .buildClient();
    }

    @Override
    public String process(String userInput) {
        try {
            // 1. Query Azure AI Search
            com.azure.search.documents.models.SearchOptions options = new com.azure.search.documents.models.SearchOptions();
            SearchPagedIterable results = searchClient.search(userInput, options, null);
            StringBuilder docs = new StringBuilder();
            for (com.azure.search.documents.models.SearchResult result : results) {
                java.util.Map<String, Object> doc = result.getDocument(java.util.Map.class);
                docs.append(doc.toString()).append("\n");
            }

            // 2. Use LLM to perform RAG on retrieved documents
            String ragPrompt = "User question: " + userInput + "\nRelevant documents:\n" + docs.toString() + "\nAnswer the question using the provided documents.";
            String answer = callLLM(ragPrompt);
            return answer;
        } catch (Exception e) {
            return "Error in SearchRAGAgent: " + e.getMessage();
        }
    }

    // Helper to call LLM (Azure OpenAI)
    private String callLLM(String prompt) {
        // You may want to inject OpenAIClient here, or use REST API
        // For now, return prompt for demonstration
        return prompt;
    }
    }
