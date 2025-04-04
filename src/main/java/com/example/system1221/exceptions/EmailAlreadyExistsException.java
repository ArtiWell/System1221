package com.example.system1221.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("Email '" + email + "' already exists");
    }

    public EmailAlreadyExistsException(String email, Throwable cause) {
        super("Email '" + email + "' already exists", cause);
    }
}
