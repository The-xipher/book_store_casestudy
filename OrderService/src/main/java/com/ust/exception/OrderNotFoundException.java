package com.ust.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String s) {
        super(s);
    }
}
