package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageStatusChecker {

    private String message;

    public boolean getMessageStatus(Message m) {
        if (m.getTimestamp()==null ) {
            message = "timestamp";
            return false;
        } else if (m.getUsername()==null) {
            message = "username";
            return false;
        } else if (m.getText()==null) {
            message = "text";
            return false;
        } else {
            return true;
        }
    }

    public String getMessage() {
        return message;
    }
}
