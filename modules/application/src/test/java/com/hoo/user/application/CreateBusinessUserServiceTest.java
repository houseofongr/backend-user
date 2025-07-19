package com.hoo.user.application;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.common.IssueIDPort;
import com.hoo.user.api.in.web.command.CreateBusinessUserCommand;
import com.hoo.user.api.in.web.result.CreateBusinessUserResult;
import com.hoo.user.api.out.cache.LoadEmailAuthnPort;
import com.hoo.user.api.out.persistence.HandleUserEventPort;
import com.hoo.user.api.out.security.PasswordEncryptPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateBusinessUserServiceTest {

    IssueIDPort issueIDPort = mock();
    LoadEmailAuthnPort loadEmailAuthnPort = mock();
    PasswordEncryptPort passwordEncryptPort = mock();
    HandleUserEventPort handleUserEventPort = mock();

    CreateBusinessUserService sut = new CreateBusinessUserService(issueIDPort, loadEmailAuthnPort, passwordEncryptPort, handleUserEventPort);

    @Test
    @DisplayName("비즈니스 사용자 생성 서비스")
    void createBusinessUserService() {
        // given
        CreateBusinessUserCommand command = new CreateBusinessUserCommand("test@example.com", "test2143!", "leaf", true, true);

        // when
        when(issueIDPort.issueNewID()).thenReturn(UuidCreator.getTimeOrderedEpoch());
        when(loadEmailAuthnPort.isAuthenticated("test@example.com")).thenReturn(true);
        when(passwordEncryptPort.encrypt("test2143!")).thenReturn("encrypt:test2143!");
        sut.create(command);

        // then
        verify(handleUserEventPort, times(1)).handleUserCreateEvent(any(), any());
    }
}