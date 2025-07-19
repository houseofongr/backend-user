package com.hoo.user.application;

import com.hoo.user.api.in.web.result.CreateEmailAuthnCodeResult;
import com.hoo.user.api.in.web.usecase.CreateEmailAuthnCodeUseCase;
import com.hoo.user.api.out.cache.SaveEmailAuthnPort;
import com.hoo.user.api.out.mail.SendAuthnCodePort;
import com.hoo.user.application.config.EmailProperties;
import com.hoo.user.application.exception.ApplicationErrorCode;
import com.hoo.user.application.exception.UserApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateEmailAuthnCodeService implements CreateEmailAuthnCodeUseCase {

    private final SaveEmailAuthnPort saveEmailAuthnPort;
    private final SendAuthnCodePort sendAuthnCodePort;
    private final EmailProperties emailProperties;

    @Override
    public CreateEmailAuthnCodeResult createEmailAuthnCode(String email) {

        validate(email);

        String authnCode = createCode();

        sendAuthnCodePort.sendAuthnCode(email, authnCode);
        saveEmailAuthnPort.saveEmailAuthnCode(email, authnCode, Duration.ofSeconds(emailProperties.authn().code().ttl()));

        return new CreateEmailAuthnCodeResult(
                String.format("[이메일 : %s]로 인증코드 전송이 완료되었습니다.", email),
                emailProperties.authn().code().ttl());
    }

    private String createCode() {
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 6; i++) builder.append(random.nextInt(10));

            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            throw new UserApplicationException(ApplicationErrorCode.INVALID_EMAIL_ADDRESS);
    }
}
