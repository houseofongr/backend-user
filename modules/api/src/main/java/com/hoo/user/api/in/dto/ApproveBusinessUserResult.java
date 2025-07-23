package com.hoo.user.api.in.dto;

import java.util.UUID;

public record ApproveBusinessUserResult(
        UUID userID,
        String nickname,
        Boolean isApproved
) {
}
