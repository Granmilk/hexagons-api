package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.exception.InvalidMatchException;
import com.gtbr.hexapi.exception.ObjectNotFoundException;
import com.gtbr.hexapi.exception.UnauthorizedException;
import com.gtbr.hexapi.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidMatchException.class)
    public ResponseEntity<String> invalidMatch(InvalidMatchException invalidMatchException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(invalidMatchException.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> objectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        return ResponseEntity
                .status(Objects.isNull(objectNotFoundException.getHttpStatus()) ? HttpStatus.NOT_FOUND : objectNotFoundException.getHttpStatus())
                .body(objectNotFoundException.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> emailAlreadyTaken(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorizedException(UnauthorizedException unauthorizedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
    }
}
