package com.hoo.user.api.in.web.usecase;

import com.hoo.user.api.in.web.result.LoginBusinessUserResult;

public interface LoginBusinessUserUseCase {
    LoginBusinessUserResult login(String email, String password);
}
