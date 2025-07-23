package com.hoo.user.domain.event;

import com.hoo.user.domain.User.UserID;
import com.hoo.user.domain.vo.UserStatus;

import java.time.ZonedDateTime;

public record BusinessUserApproveEvent(
        UserID userID,
        UserStatus status,
        ZonedDateTime approveAt
) {

}
