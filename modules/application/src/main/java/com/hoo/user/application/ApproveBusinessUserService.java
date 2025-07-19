package com.hoo.user.application;

import com.hoo.user.api.in.web.result.ApproveBusinessUserResult;
import com.hoo.user.api.in.web.usecase.ApproveBusinessUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ApproveBusinessUserService implements ApproveBusinessUserUseCase {

    @Override
    public ApproveBusinessUserResult confirm(List<UUID> userIDs) {
        return null;
    }
}
