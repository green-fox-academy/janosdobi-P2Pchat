package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import com.greenfox.oraclechat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    MessageService messages;

    @PostMapping("/api/message/receive")
    @CrossOrigin("*")
    public ResponseEntity receiveMessage(@RequestBody Message message, Client client) {
        messages.addMessage(message);
        if (message.getCreatedAt()==null||message.getText()==null||message.getUserName()==null) {
            return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(new Status("ok"), HttpStatus.OK);
    }
}