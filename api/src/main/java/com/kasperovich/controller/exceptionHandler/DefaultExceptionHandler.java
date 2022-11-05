package com.kasperovich.controller.exceptionHandler;

import com.kasperovich.util.UUIDGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, ErrorContainer>> exception(Exception exception){
        return new ResponseEntity<>(Collections.singletonMap("error", ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorCode(2)
                .errorMessage("General Error")
                .e(exception.getClass().toString())
                .build()), HttpStatus.BAD_REQUEST);
    }
}
