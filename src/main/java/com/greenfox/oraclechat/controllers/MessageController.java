package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Holder;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import com.greenfox.oraclechat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageController {

    @Autowired
    MessageService messages;

    @PostMapping("/api/message/receive")
    @CrossOrigin("*")
    public ResponseEntity receiveMessage(@RequestBody Holder holder) {
        if (holder.getMessage().getCreatedAt()==null||holder.getMessage().getText()==null||holder.getMessage().getUserName()==null) {
            return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
        } else {
            messages.addMessage(holder.getMessage());
            return new ResponseEntity(new Status("ok"), HttpStatus.OK);
        }
    }

    @GetMapping("/index/addmessage")
    public ResponseEntity<Holder> sendMessage() {
        RestTemplate restTemplate = new RestTemplate();
        Message m = new Message("Jani", "testmessage");
        Client c = new Client("janosdobi");
        HttpEntity<Holder> request = new HttpEntity<>(new Holder(m,c));
        ResponseEntity<Holder> response = restTemplate
                .exchange("http://oraclechat.herokuapp.com/api/message/receive", HttpMethod.POST, request, Holder.class);
        return response;
    }
}