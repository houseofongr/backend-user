package com.hoo.user.application.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserAdapterException extends RuntimeException {
    private final AdapterErrorCode error;
    private final String message;

    public UserAdapterException(AdapterErrorCode error) {
        log.error("Application Error : {}", error.getMessage());
        this.error = error;
        this.message = error.getMessage();
    }

    public UserAdapterException(AdapterErrorCode error, String message) {
        log.error("Application Error : {}", message);
        this.error = error;
        this.message = message;
    }

}
