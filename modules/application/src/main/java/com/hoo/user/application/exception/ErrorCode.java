package com.hoo.user.application.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();

    String getCode();

    HttpStatus getStatus();

    String getMessage();
}
