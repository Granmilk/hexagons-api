package com.gtbr.hexapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ObjectNotFoundException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public ObjectNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
