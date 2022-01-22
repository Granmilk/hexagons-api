package com.gtbr.hexapi.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("This e-mail has already taken");
    }
}
