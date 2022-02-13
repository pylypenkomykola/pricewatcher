package com.mykolapylypenko.pricewatcher.domain.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {

    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
