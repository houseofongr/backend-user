package com.hoo.user.domain.event;

import com.hoo.user.domain.User.UserID;

import java.time.ZonedDateTime;

public record BusinessUserApproveEvent(
        UserID userID,
        ZonedDateTime approveAt
) {

}
