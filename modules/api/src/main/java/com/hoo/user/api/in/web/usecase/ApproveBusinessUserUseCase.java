package com.hoo.user.api.in.web.usecase;

import com.hoo.user.api.in.web.result.ApproveBusinessUserResult;

import java.util.List;
import java.util.UUID;

public interface ApproveBusinessUserUseCase {
    ApproveBusinessUserResult confirm(List<UUID> userIDs);
}
