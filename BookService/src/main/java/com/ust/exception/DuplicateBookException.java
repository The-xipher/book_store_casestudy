package com.ust.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String s) {
        super(s);
    }
}
