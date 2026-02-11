package com.chetraseng.taskflow_api.exception;

public class EmailExistException extends RuntimeException {
    public EmailExistException(String message) {
        super(message);
    }
}
