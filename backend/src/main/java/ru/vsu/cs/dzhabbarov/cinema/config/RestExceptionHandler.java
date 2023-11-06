package ru.vsu.cs.dzhabbarov.cinema.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vsu.cs.dzhabbarov.cinema.exception.RestException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RestException.class})
    @ResponseBody
    public ResponseEntity<RestExceptionResponse> handler(RestException ex){
        return ResponseEntity.status(ex.getStatus())
                .body(RestExceptionResponse.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }
}
