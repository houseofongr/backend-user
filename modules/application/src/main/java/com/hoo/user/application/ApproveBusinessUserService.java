package com.hoo.user.application;

import com.hoo.user.api.in.dto.ApproveBusinessUserResult;
import com.hoo.user.api.in.ApproveBusinessUserUseCase;
import com.hoo.user.api.out.HandleUserEventPort;
import com.hoo.user.api.out.LoadUserPort;
import com.hoo.user.domain.User;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ApproveBusinessUserService implements ApproveBusinessUserUseCase {

    private final LoadUserPort loadUserPort;
    private final HandleUserEventPort handleUserEventPort;

    @Override
    public ApproveBusinessUserResult approve(UUID userID, Boolean approve) {

        User businessUser = loadUserPort.loadBusinessUser(userID);
        BusinessUserApproveEvent event = businessUser.approve(approve);

        handleUserEventPort.handleBusinessUserApprove(event);

        return new ApproveBusinessUserResult(
                businessUser.getId().uuid(),
                businessUser.getUserMetadata().getNickname(),
                approve
        );
    }
}
