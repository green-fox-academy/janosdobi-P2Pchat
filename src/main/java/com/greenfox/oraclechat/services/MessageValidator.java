package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.Status;
import org.springframework.stereotype.Service;

@Service
public class MessageValidator {

    public Status validateMessage(Message m) {
        if (m.getCreatedAt()==null) {
            return new Status("error", "Message timestamp missing!");
        } else if (m.getUserName()==null) {
            return new Status("error", "Username missing!");
        } else if (m.getText()==null) {
            return new Status("error", "Message text missing!");
        } else {
            return new Status("ok");
        }
    }


}
