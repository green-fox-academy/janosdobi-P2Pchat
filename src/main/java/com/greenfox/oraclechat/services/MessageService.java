package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.OraclechatApplication;
import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Holder;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import com.greenfox.oraclechat.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

    public ResponseEntity<Status> sendMessage(Message m, Client c) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Holder> request = new HttpEntity<>(new Holder(m, c));
        ResponseEntity<Status> response = restTemplate.postForObject(OraclechatApplication.CHAT_APP_PEER_ADDRESS, request, ResponseEntity.class);
        return response;
    }
}