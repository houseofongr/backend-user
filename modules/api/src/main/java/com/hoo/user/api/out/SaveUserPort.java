package com.hoo.user.api.out;

import com.hoo.user.domain.event.BusinessUserCreateEvent;

public interface SaveUserPort {
    void saveBusinessUser(BusinessUserCreateEvent event);
}
