package com.hoo.user.api.in.web.usecase;

import com.hoo.user.api.in.web.result.CreateEmailAuthnCodeResult;

public interface CreateEmailAuthnCodeUseCase {
    CreateEmailAuthnCodeResult create(String email);
}
