package com.busbooking.controller;

import com.busbooking.dto.ChatRequest;
import com.busbooking.dto.ChatResponse;
import com.busbooking.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String reply = chatBotService.processQuery(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(reply));
    }
}