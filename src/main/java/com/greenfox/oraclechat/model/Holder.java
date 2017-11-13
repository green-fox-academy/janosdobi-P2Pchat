package com.greenfox.oraclechat.model;

public class Holder {

    private Message message;
    private Client client;

    public Holder(Message message, Client client) {
        this.message = message;
        this.client = client;
    }

    public Holder() {
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
