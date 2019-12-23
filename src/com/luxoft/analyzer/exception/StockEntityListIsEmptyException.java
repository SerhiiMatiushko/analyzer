package com.luxoft.analyzer.exception;

public class StockEntityListIsEmptyException extends RuntimeException {
    private String errorMessage;

    public StockEntityListIsEmptyException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
