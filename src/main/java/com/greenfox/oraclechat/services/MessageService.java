package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Holder;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Test;
import com.greenfox.oraclechat.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public Iterable<Message> listAll() {
        return messageRepo.findAll();
    }

    public Message getMessage(long id) {
        return messageRepo.findOne(id);
    }

    public void addMessage (Message m) {
        messageRepo.save(m);
    }

    public ResponseEntity<Holder> sendMessage(Message m, Client c) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Holder> request = new HttpEntity<>(new Holder(m, c));
        ResponseEntity<Holder> response = restTemplate
                .exchange("https://chatfactory.herokuapp.com/api/message/receive", HttpMethod.POST, request, Holder.class);
        return response;
    }
}