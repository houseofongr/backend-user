package com.hoo.user.api.out;

import com.hoo.user.domain.event.BusinessUserApproveEvent;

public interface UpdateUserStatusPort {
    void updateBusinessUserApprove(BusinessUserApproveEvent event);
}
