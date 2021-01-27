package com.example.threerivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class InvalidAccountException extends HttpClientErrorException {

    public InvalidAccountException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
