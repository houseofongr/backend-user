package com.hoo.user.api.in.dto;

import java.util.UUID;

public record RegisterBusinessUserResult(
        UUID userID,
        String email,
        String nickname,
        Boolean termsOfUseConsent,
        Boolean personalInfoConsent
) {
}
