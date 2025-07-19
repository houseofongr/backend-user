package com.hoo.user.api.in.web.result;

import java.util.UUID;

public record LoginBusinessUserResult(
        UUID userID,
        String email,
        String nickname,
        String accessToken
) {
}
