package com.developer.exception;

public class InvalidParamExeption extends Exception {
    private String message;
    public InvalidParamExeption(String message){
        this.message=message;

    }
    public String getMessage(){
        return message;
    }
}
