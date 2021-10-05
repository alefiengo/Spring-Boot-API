package com.alefiengo.springboot.api.controller.exception;

@Deprecated
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
