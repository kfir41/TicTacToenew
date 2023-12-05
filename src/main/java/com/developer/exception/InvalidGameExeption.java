package com.developer.exception;

public class InvalidGameExeption extends Exception{
    private String message;
    public InvalidGameExeption(String message)
    {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}