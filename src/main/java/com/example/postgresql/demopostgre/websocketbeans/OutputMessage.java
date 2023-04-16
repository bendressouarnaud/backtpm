package com.example.postgresql.demopostgre.websocketbeans;

public class OutputMessage {

    private String message;

    public OutputMessage(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
