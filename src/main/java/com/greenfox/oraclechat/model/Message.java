package com.greenfox.oraclechat.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    private long id;
    private String username;
    private String text;
    private Timestamp timestamp;

    public Message(String username, String text) {
        this.id=(int) (1000000 + (Math.random() * 9999999));
        this.username = username;
        this.text = text;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message(long id, String username, String text, Timestamp timestamp) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(String username) {
        this.id=(int) (1000000 + (Math.random() * 9999999));
        this.username = username;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message() {
        this.id=(int) (1000000 + (Math.random() * 9999999));
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}