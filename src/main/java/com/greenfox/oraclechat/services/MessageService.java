package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public Iterable<Message> listAll() {
        return messageRepo.findAll();
    }
}
