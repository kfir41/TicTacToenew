package com.developer.exception;

public class InvalidMove extends Exception {
    private String message;
    public InvalidMove(String message){
        this.message=message;

    }
    public String getMessage(){
        return message;
    }
}