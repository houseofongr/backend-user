package com.hoo.user.api.in.web.usecase;

import com.hoo.user.api.in.web.result.VerifyEmailAuthnCodeResult;

public interface VerifyEmailAuthnCodeUseCase {
    VerifyEmailAuthnCodeResult verify(String email, String code);
}
