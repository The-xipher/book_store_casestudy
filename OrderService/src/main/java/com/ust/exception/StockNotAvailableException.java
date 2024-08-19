package com.ust.exception;

public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(String s) {
        super(s);
    }
}
