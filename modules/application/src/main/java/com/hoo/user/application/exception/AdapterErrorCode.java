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
public enum AdapterErrorCode implements ErrorCode {

    USER_NOT_FOUND("UNIVERSE-PRESENTATION-300", NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    LOAD_ENTITY_FAILED_BY_DOMAIN_ID("UNIVERSE-PRESENTATION-500", INTERNAL_SERVER_ERROR, "도메인 객체의 ID를 통해 엔티티를 불러오는데 실패했습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

}
