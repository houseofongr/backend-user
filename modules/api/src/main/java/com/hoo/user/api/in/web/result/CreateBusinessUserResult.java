package com.hoo.user.api.in.web.result;

import java.util.UUID;

public record CreateBusinessUserResult(
        UUID userID,
        String email,
        String nickname,
        Boolean termsOfUseConsent,
        Boolean personalInfoConsent
) {
}
