package com.flower.exception;

public class UserServiceException extends Exception {

    public UserServiceException() {
        super();
    }

    public UserServiceException(final String message) {
        super(message);
    }

}
