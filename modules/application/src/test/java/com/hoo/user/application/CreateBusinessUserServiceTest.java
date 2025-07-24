package com.hoo.user.application;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.common.IssueIDPort;
import com.hoo.common.internal.api.auth.RegisterBusinessUserCredentialAPI;
import com.hoo.user.api.in.dto.RegisterBusinessUserCommand;
import com.hoo.user.api.out.HandleUserEventPort;
import com.hoo.user.api.out.LoadEmailAuthnPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateBusinessUserServiceTest {

    IssueIDPort issueIDPort = mock();
    LoadEmailAuthnPort loadEmailAuthnPort = mock();
    HandleUserEventPort handleUserEventPort = mock();
    RegisterBusinessUserCredentialAPI registerBusinessUserCredentialAPI = mock();

    RegisterBusinessUserService sut = new RegisterBusinessUserService(issueIDPort, loadEmailAuthnPort, handleUserEventPort, registerBusinessUserCredentialAPI);

    @Test
    @DisplayName("비즈니스 사용자 생성 서비스")
    void createBusinessUserService() {
        // given
        RegisterBusinessUserCommand command = new RegisterBusinessUserCommand("test@example.com", "test2143!", "leaf", true, true);

        // when
        when(issueIDPort.issueNewID()).thenReturn(UuidCreator.getTimeOrderedEpoch());
        when(loadEmailAuthnPort.isAuthenticated("test@example.com")).thenReturn(true);
        sut.create(command);

        // then
        verify(handleUserEventPort, times(1)).handleBusinessUserCreate(any());
        verify(registerBusinessUserCredentialAPI, times(1)).registerBusinessUserCredential("test@example.com", "test2143!");
    }
}