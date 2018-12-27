package org.domain.user.registration.exceptions;

public class UserIdNotAvailableException extends RuntimeException{

    public UserIdNotAvailableException(String message) {
        super(message);
    }
}
