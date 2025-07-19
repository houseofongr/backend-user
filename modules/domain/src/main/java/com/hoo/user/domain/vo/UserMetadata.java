package com.hoo.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMetadata {

    private final Boolean termsOfUseConsent;
    private final Boolean personalInfoConsent;
    private final UserType type;
    private final UserStatus status;

    public UserMetadata approve() {
        return new UserMetadata(termsOfUseConsent, personalInfoConsent, type, UserStatus.ACTIVATE);
    }
}
