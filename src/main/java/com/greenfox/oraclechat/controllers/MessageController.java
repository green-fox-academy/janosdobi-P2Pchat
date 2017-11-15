package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.OraclechatApplication;
import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Holder;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import com.greenfox.oraclechat.services.MessageService;
import com.greenfox.oraclechat.services.MessageStatusChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RestController
public class MessageController {

    @Autowired
    MessageService messages;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageStatusChecker statusChecker;

    @PostMapping("/api/message/receive")
    @CrossOrigin("*")
    public ResponseEntity receiveMessage(@RequestBody Holder holder) {
        Message incomingMessage = holder.getMessage();
        Client messageSender = holder.getClient();
        if(statusChecker.getMessageStatus(incomingMessage)&&!(messageSender.getId()== OraclechatApplication.CHAT_APP_UNIQUE_ID)) {
            messages.addMessage(incomingMessage);
            simpMessagingTemplate.convertAndSend("/socket", "");
            return new ResponseEntity(new Status("ok"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Status("error", "Something is wrong with your message!"), HttpStatus.UNAUTHORIZED);
        }

    }
}