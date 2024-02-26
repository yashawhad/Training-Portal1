package com.capgemini.trainingPortal.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
	
	public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

}
