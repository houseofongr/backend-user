package com.hoo.user.application;

import com.hoo.common.internal.api.user.GetUserInfoAPI;
import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.api.out.FindUserInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUserInfoService implements GetUserInfoAPI {

    private final FindUserInfoPort findUserInfoPort;

    @Override
    public UserInfo getUserInfo(UUID userID) {
        return findUserInfoPort.findUserInfo(userID);
    }
}
