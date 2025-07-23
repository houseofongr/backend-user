package com.hoo.user.api.in;

import com.hoo.user.api.in.dto.RegisterBusinessUserCommand;
import com.hoo.user.api.in.dto.RegisterBusinessUserResult;

public interface RegisterBusinessUserUseCase {
    RegisterBusinessUserResult create(RegisterBusinessUserCommand command);
}
