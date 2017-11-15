package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageStatusChecker {

    public boolean getMessageStatus(Message m) {
        if (m.getTimestamp()==null ) {
            return false;
        } else if (m.getUserName()==null) {
            return false;
        } else if (m.getText()==null) {
            return false;
        } else {
            return true;
        }
    }
}
