package ru.otus.timofeev.task3.exception;

public abstract class AbstractWithRejectedFieldException extends RuntimeException {

    private String rejectedValue;

    public AbstractWithRejectedFieldException(String message, String rejectedValue) {
        super(message);
        this.rejectedValue = rejectedValue;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}
