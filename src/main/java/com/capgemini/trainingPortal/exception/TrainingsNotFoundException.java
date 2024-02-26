package com.capgemini.trainingPortal.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainingsNotFoundException extends RuntimeException {
    public TrainingsNotFoundException(String message) {
        super(message);
    }
}
