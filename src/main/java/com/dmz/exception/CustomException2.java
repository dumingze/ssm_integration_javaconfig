package com.dmz.exception;

import lombok.Data;

@Data
public class CustomException2 extends Exception{
    String username;

    public CustomException2(String message, String username) {
        super(message);
        this.username = username;
    }
}
