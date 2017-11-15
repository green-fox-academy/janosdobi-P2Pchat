package com.greenfox.oraclechat.model;

public class IncomingClientMessage {

    private Message message;
    private Client client;

    public IncomingClientMessage(Message message, Client client) {
        this.message = message;
        this.client = client;
    }

    public IncomingClientMessage() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
