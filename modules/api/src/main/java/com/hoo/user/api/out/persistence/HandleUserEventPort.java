package com.hoo.user.api.out.persistence;

import com.hoo.user.domain.event.BusinessUserCreateEvent;

public interface HandleUserEventPort {
    void handleUserCreateEvent(BusinessUserCreateEvent event, String encryptPassword);
}
