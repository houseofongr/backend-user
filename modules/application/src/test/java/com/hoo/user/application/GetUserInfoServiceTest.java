package com.hoo.user.application;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.api.out.FindUserInfoPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUserInfoServiceTest {

    FindUserInfoPort findUserInfoPort = mock();

    GetUserInfoService sut = new GetUserInfoService(findUserInfoPort);

    @Test
    @DisplayName("사용자 정보 조회 서비스")
    void getUserInfoService() {
        // given
        UUID userID = UuidCreator.getTimeOrderedEpoch();

        // when
        sut.getUserInfo(userID);

        // then
        verify(findUserInfoPort, times(1)).findUserInfo(userID);
    }

}