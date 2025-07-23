package com.hoo.user.adapter.in.web.errorcode;

import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.ApplicationErrorCode;
import com.hoo.user.application.exception.DomainErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorCodeController {

    @GetMapping("/users/domain-error-codes")
    ResponseEntity<?> getDomainErrorCodes() {
        Map<String, ErrorResponse> responseMap = new HashMap<>();

        for (DomainErrorCode errorCode : DomainErrorCode.values())
            responseMap.put(errorCode.name(), ErrorResponse.of(errorCode));

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/users/application-error-codes")
    ResponseEntity<?> getApplicationErrorCodes() {
        Map<String, ErrorResponse> responseMap = new HashMap<>();

        for (ApplicationErrorCode errorCode : ApplicationErrorCode.values())
            responseMap.put(errorCode.name(), ErrorResponse.of(errorCode));

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/users/adapter-error-codes")
    ResponseEntity<?> getAdapterErrorCodes() {
        Map<String, ErrorResponse> responseMap = new HashMap<>();

        for (AdapterErrorCode errorCode : AdapterErrorCode.values())
            responseMap.put(errorCode.name(), ErrorResponse.of(errorCode));

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
