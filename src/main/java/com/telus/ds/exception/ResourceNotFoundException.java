package com.telus.ds.exception;

import lombok.Setter;

@Setter
public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException(String message){
        super(message);
        this.message = message;
    }

}