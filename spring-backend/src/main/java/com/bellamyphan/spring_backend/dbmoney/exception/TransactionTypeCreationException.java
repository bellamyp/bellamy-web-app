package com.bellamyphan.spring_backend.dbmoney.exception;

public class TransactionTypeCreationException extends RuntimeException {
    public TransactionTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}