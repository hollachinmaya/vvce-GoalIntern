package com.hari.InternPrep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Endpoint to send notifications (you can call this when new data is fetched)
    @PostMapping("/send")
    public void sendNotification(@RequestBody String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}