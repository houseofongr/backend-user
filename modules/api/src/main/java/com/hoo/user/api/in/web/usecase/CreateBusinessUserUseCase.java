package com.hoo.user.api.in.web.usecase;

import com.hoo.user.api.in.web.command.CreateBusinessUserCommand;
import com.hoo.user.api.in.web.result.CreateBusinessUserResult;

public interface CreateBusinessUserUseCase {
    CreateBusinessUserResult create(CreateBusinessUserCommand command);
}
