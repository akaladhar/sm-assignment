package com.sm.assignment.exception;

public class ProcessingException extends RuntimeException {

    private String message;
    public ProcessingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
