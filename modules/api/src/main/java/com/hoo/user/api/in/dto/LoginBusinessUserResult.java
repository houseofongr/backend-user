package com.hoo.user.api.in.dto;

import java.util.UUID;

public record LoginBusinessUserResult(
        UUID userID,
        String email,
        String nickname,
        String accessToken
) {
}
