package com.luxoft.analyzer.exception;

public class ResultEntityIsNotValidException extends RuntimeException {
    private String errorMessage;

    public ResultEntityIsNotValidException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
