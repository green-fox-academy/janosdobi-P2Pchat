package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.OraclechatApplication;
import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.IncomingClientMessage;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import com.greenfox.oraclechat.services.MessageService;
import com.greenfox.oraclechat.services.MessageStatusChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

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
    public ResponseEntity receiveMessage(@RequestBody IncomingClientMessage icm) {
        Message m = icm.getMessage();
        Client c = icm.getClient();
        if(statusChecker.getMessageStatus(m)&&(c.getId()!=OraclechatApplication.CHAT_APP_UNIQUE_ID)) {
            messages.addMessage(m);
            simpMessagingTemplate.convertAndSend("/socket", "");
            /*RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(OraclechatApplication.CHAT_APP_PEER_ADDRESS, incomingMessage, Status.class);*/
            return new ResponseEntity(new Status("ok"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Status("error", "Something is wrong with your message!"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/api/messages")
    public Iterable<Message> getMessages() {
        return messages.listTenMostRecent();
    }
}