package com.kasperovich.controller.exceptionHandler;

import com.kasperovich.exception.BadPasswordException;
import com.kasperovich.exception.UnableToDeleteProductException;
import com.kasperovich.util.UUIDGenerator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, ErrorContainer>> allException(Exception exception){
        return new ResponseEntity<>(Collections.singletonMap("error", ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorCode(2)
                .errorMessage("General Error")
                .e(exception.getClass().toString())
                .build()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BadPasswordException.class,
            UnableToDeleteProductException.class,
            NumberFormatException.class
    })
    public ResponseEntity<Map<String, ErrorContainer>> handledException(Exception exception){
        return new ResponseEntity<>(Collections.singletonMap("error", ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorCode(2)
                .errorMessage(exception.getMessage())
                .e(exception.getClass().toString())
                .build()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
//            NoSuchEntityException.class,
            EmptyResultDataAccessException.class,
            NoSuchElementException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<Object> handlerEntityNotFoundException(Exception e) {

        ErrorContainer error =
                ErrorContainer.builder()
                        .exceptionId(UUIDGenerator.generateUUID())
                        .errorCode(2)
                        .errorMessage(e.getMessage())
                        .e(e.getClass().toString())
                        .build();
        return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.NOT_FOUND);
    }

}
