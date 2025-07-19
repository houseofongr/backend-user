package com.hoo.user.application;

import com.hoo.user.api.in.web.result.VerifyEmailAuthnCodeResult;
import com.hoo.user.api.in.web.usecase.VerifyEmailAuthnCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyEmailAuthnCodeService implements VerifyEmailAuthnCodeUseCase {

    @Override
    public VerifyEmailAuthnCodeResult verify(String email, String code) {
        return null;
    }
}
