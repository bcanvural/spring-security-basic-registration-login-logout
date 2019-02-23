package com.github.bcanvural.basicregistration.exceptions;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message){
        super(message);
    }
}
