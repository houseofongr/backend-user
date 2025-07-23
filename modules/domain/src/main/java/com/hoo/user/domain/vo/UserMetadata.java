package com.hoo.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMetadata {

    private final String nickname;
    private final Boolean termsOfUseConsent;
    private final Boolean personalInfoConsent;
    private final UserType type;
    private final UserStatus status;

    public UserMetadata approve() {
        return new UserMetadata(nickname, termsOfUseConsent, personalInfoConsent, type, UserStatus.ACTIVATE);
    }

    public UserMetadata reject() {
        return new UserMetadata(nickname, termsOfUseConsent, personalInfoConsent, type, UserStatus.REJECTED);
    }
}
