package com.example.fileupload.controller;

public class UserAlreadyFoundException extends Exception{

    public UserAlreadyFoundException() {
    }

    public UserAlreadyFoundException(String message) {
        super(message);
    }
}
