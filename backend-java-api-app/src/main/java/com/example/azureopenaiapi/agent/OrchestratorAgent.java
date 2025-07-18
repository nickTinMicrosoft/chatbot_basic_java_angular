package com.example.azureopenaiapi.agent;

import java.util.List;

public class OrchestratorAgent implements Agent {
    private List<Agent> agents;

    public OrchestratorAgent() {
        this.agents = List.of(new ChatAgent(), new SearchRAGAgent(), new OracleDbRAGAgent());
    }

    @Override
    public String process(String userInput) {
        // Decide which agent(s) to use based on userInput
        // ...implementation...
        return "Orchestrated response";
    }
}
