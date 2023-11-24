package com.github.gifarj.cinema.exception;

import org.springframework.http.HttpStatus;

public class IncorrectCredentialsException extends RestException {
    public IncorrectCredentialsException() {
        super("Incorrect credentials", HttpStatus.BAD_REQUEST);
    }
}
