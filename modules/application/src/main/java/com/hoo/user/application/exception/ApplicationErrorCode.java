package com.hoo.user.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

/**
 * <pre>
 * <h1>Error Code Index</h1>
 * <ul>
 *     <li>Bad Request(400) : 1 ~ 99</li>
 *     <li>Authentication(401) : 100 ~ 199</li>
 *     <li>Not Found(404) : 300 ~ 399</li>
 *     <li>Conflict(405) : 400 ~ 499</li>
 *     <li>Internal Server Error(500) : 500 ~ 599</li>
 *     <li>Etc : 600 ~</li>
 * </ul>
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements ErrorCode {

    INVALID_EMAIL_ADDRESS("AUTH-APPLICATION-1", BAD_REQUEST, "잘못된 이메일 형식입니다."),
    UNAUTHORIZED_EMAIL("AUTH-APPLICATION-2", UNAUTHORIZED, "인증되지 않은 이메일 주소입니다."),
    EMAIL_CODE_AUTHENTICATION_FAILED("AUTH-APPLICATION-3", UNAUTHORIZED, "이메일 인증에 실패했습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

}
