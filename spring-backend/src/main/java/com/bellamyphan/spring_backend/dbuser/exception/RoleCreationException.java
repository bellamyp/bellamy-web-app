package com.bellamyphan.spring_backend.dbuser.exception;

public class RoleCreationException extends RuntimeException {
    public RoleCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
