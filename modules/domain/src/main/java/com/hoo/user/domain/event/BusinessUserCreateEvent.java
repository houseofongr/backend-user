package com.hoo.user.domain.event;

import com.hoo.user.domain.User;

public record BusinessUserCreateEvent(
        User newBusinessUser
) {
}
