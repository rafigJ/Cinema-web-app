package com.github.gifarj.cinema.exception;

import org.springframework.http.HttpStatus;

public class NotExistUserException extends RestException {
    public NotExistUserException() {
        super("Unknown user", HttpStatus.NOT_FOUND);
    }
}
