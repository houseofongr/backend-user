package com.hoo.user.api.in;

import com.hoo.user.api.in.dto.ApproveBusinessUserResult;

import java.util.UUID;

public interface ApproveBusinessUserUseCase {
    ApproveBusinessUserResult approve(UUID userID, Boolean approve);
}
