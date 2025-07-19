package com.hoo.user.application;

import com.hoo.user.api.out.cache.SaveEmailAuthnPort;
import com.hoo.user.api.out.mail.SendAuthnCodePort;
import com.hoo.user.application.config.EmailProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateEmailAuthnCodeServiceTest {

    SaveEmailAuthnPort saveEmailAuthnPort = mock();
    SendAuthnCodePort sendAuthnCodePort = mock();
    EmailProperties emailProperties = new EmailProperties(
            new EmailProperties.Authn(
                    new EmailProperties.Authn.Code(600), new EmailProperties.Authn.Status(3600))
    );

    CreateEmailAuthnCodeService sut = new CreateEmailAuthnCodeService(saveEmailAuthnPort, sendAuthnCodePort, emailProperties);

    @Test
    @DisplayName("이메일 인증 코드 생성 서비스")
    void createEmailAuthnCodeService() {
        // given
        String email = "test@example.com";

        // when
        sut.createEmailAuthnCode(email);

        // then
        verify(sendAuthnCodePort, times(1)).sendAuthnCode(anyString(), anyString());
        verify(saveEmailAuthnPort, times(1)).saveEmailAuthnCode(anyString(), anyString(), any());
    }

}