package com.gtbr.hexapi.exception;

public class InvalidMatchException extends RuntimeException {

    public InvalidMatchException() {
        super("This match is invalid");
    }
}
