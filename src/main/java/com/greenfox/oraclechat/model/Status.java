package com.greenfox.oraclechat.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    private String status;
    private String message;

    public Status(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status(String status) {
        this.status = status;
    }

    public Status() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
