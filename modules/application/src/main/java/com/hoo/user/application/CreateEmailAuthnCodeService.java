package com.hoo.user.application;

import com.hoo.user.api.in.web.result.CreateEmailAuthnCodeResult;
import com.hoo.user.api.in.web.usecase.CreateEmailAuthnCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmailAuthnCodeService implements CreateEmailAuthnCodeUseCase {

    @Override
    public CreateEmailAuthnCodeResult create(String email) {
        return null;
    }
}
