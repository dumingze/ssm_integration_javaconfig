package com.dmz.exception;

import lombok.Data;

@Data
public class CustomException extends Exception{
    String username;

    public CustomException(String message, String username) {
        super(message);
        this.username = username;
    }
}
