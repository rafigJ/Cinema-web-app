package com.github.gifarj.cinema.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.gifarj.cinema.dto.RestExceptionDto;
import com.github.gifarj.cinema.exception.RestException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RestException.class})
    @ResponseBody
    public ResponseEntity<RestExceptionDto> handler(RestException ex){
        return ResponseEntity.status(ex.getStatus())
                .body(RestExceptionDto.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }
}
