package com.greenfox.oraclechat.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name="seq", initialValue = 1000000, allocationSize = 1000000)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private long id;
    private String userName;
    private String text;
    private Timestamp createdAt;

    public Message(String userName, String text) {
        this.userName = userName;
        this.text = text;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message(String userName, String text, Timestamp createdAt) {
        this.userName = userName;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Message(String userName) {
        this.userName = userName;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message() {
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