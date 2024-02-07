package com.b3i.monsterspring.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
