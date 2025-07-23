package com.hoo.user.application;

import com.hoo.user.api.out.QueryUserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CheckExistNicknameServiceTest {

    QueryUserPort queryUserPort = mock();

    CheckExistNicknameService sut = new CheckExistNicknameService(queryUserPort);

    @Test
    @DisplayName("닉네임 존재여부 확인 서비스")
    void checkExistNicknameService() {
        // given
        String nickname = "leaf";

        // when
        sut.isExist(nickname);

        // then
        verify(queryUserPort, times(1)).existByName(nickname);
    }

}