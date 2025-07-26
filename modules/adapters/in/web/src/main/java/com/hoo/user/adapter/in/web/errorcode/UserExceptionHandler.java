package com.hoo.user.adapter.in.web.errorcode;

import com.hoo.user.application.exception.UserAdapterException;
import com.hoo.user.application.exception.UserApplicationException;
import com.hoo.user.application.exception.UserDomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<ErrorResponse> handle(UserDomainException exception) {
        return ResponseEntity.status(exception.getError().getStatus()).body(ErrorResponse.of(exception.getError()));
    }

    @ExceptionHandler(UserApplicationException.class)
    public ResponseEntity<ErrorResponse> handle(UserApplicationException exception) {
        return ResponseEntity.status(exception.getError().getStatus()).body(ErrorResponse.of(exception.getError()));
    }

    @ExceptionHandler(UserAdapterException.class)
    public ResponseEntity<ErrorResponse> handle(UserAdapterException exception) {
        return ResponseEntity.status(exception.getError().getStatus()).body(ErrorResponse.of(exception.getError()));
    }
}
