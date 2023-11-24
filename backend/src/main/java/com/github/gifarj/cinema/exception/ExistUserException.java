package com.github.gifarj.cinema.exception;

import org.springframework.http.HttpStatus;

public class ExistUserException extends RestException {
    public ExistUserException() {
        super("Username already exist", HttpStatus.BAD_REQUEST);
    }
}
