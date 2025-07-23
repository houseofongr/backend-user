package com.hoo.user.api.out;

import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;

public interface HandleUserEventPort {
    void handleBusinessUserCreate(BusinessUserCreateEvent event);

    void handleBusinessUserApprove(BusinessUserApproveEvent event);
}
