package com.inventory.manager.exception;

public class AuthenticationFailureExcetion extends RuntimeException {
    public AuthenticationFailureExcetion(String message) {
        super(message);
    }
}
