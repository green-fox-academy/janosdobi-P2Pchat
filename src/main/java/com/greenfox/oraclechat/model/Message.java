package com.greenfox.oraclechat.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    private long id;
    private String userName;
    private String text;
    private Timestamp createdAt;

    public Message(String userName, String text) {
        this.id=(int) (1000000 + Math.random() * 9999999);
        this.userName = userName;
        this.text = text;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message(long id, String userName, String text, Timestamp createdAt) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Message(String userName) {
        this.id=(int) (1000000 + Math.random() * 9999999);
        this.userName = userName;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message() {
        this.id=(int) (1000000 + Math.random() * 9999999);
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}