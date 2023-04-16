package com.example.postgresql.demopostgre.websocketbeans;

public class InputMessage {

    private String message;

    public InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
