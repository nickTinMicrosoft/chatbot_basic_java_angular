package com.nyacs.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private Kernel kernel;

    @PostMapping("/simple")
    public ResponseEntity<String> simpleChat(@RequestBody Map<String, String> body) {
        String prompt = body.get("message");

        var chatService = kernel.getService(TextGenerationService.class);

        var response = chatService.generateText(prompt).block();

        return ResponseEntity.ok(response);
    }
}
