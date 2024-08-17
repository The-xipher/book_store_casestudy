package com.ust.exception;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String s) {
        super(s);
    }
}
