package com.revature.registration.util.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException (String message) {
        super(message);
    }
}