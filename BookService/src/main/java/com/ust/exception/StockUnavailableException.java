package com.ust.exception;

public class StockUnavailableException extends RuntimeException {
    public StockUnavailableException(String s) {
        super(s);
    }
}
