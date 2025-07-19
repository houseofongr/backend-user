package com.hoo.user.application;

import com.hoo.user.api.in.web.result.LoginBusinessUserResult;
import com.hoo.user.api.in.web.usecase.LoginBusinessUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginBusinessUserService implements LoginBusinessUserUseCase {

    @Override
    public LoginBusinessUserResult login(String email, String password) {
        return null;
    }
}
