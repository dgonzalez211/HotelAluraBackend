package org.diegodev.hotelalurabackend.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
