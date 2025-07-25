package com.hoo.user.application;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.api.out.UpdateUserStatusPort;
import com.hoo.user.api.out.LoadUserPort;
import com.hoo.user.test.domain.UserTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ApproveBusinessUserServiceTest {

    LoadUserPort loadUserPort = mock();
    UpdateUserStatusPort updateUserStatusPort = mock();

    ApproveBusinessUserService sut = new ApproveBusinessUserService(loadUserPort, updateUserStatusPort);

    @Test
    @DisplayName("비즈니스 유저 승인 서비스")
    void ApproveBusinessUserService() {
        // given
        UUID userID = UuidCreator.getTimeOrderedEpoch();

        // when
        when(loadUserPort.loadBusinessUser(userID)).thenReturn(UserTestData.defaultBusinessUser().build());
        sut.approve(userID, true);

        // then
        verify(updateUserStatusPort, times(1)).updateBusinessUserApprove(any());
    }
}