package com.example.azureopenaiapi.controller;

import com.example.azureopenaiapi.agent.OrchestratorAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OrchestratorAgent orchestratorAgent;

    @Autowired
    public ChatController(OrchestratorAgent orchestratorAgent) {
        this.orchestratorAgent = orchestratorAgent;
    }

    @PostMapping
    public String chat(@RequestBody String userInput) {
        return orchestratorAgent.process(userInput);
    }
}
