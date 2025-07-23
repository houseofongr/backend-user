package com.hoo.user.api.out;

import com.hoo.common.internal.api.user.dto.UserInfo;

import java.util.UUID;

public interface FindUserInfoPort {
    UserInfo findUserInfo(UUID userID);
}
