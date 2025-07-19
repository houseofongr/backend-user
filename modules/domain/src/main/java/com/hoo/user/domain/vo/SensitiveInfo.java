package com.hoo.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SensitiveInfo {

    private final String realName;
    private final String email;
    private final String phoneNumber;
}
