package com.hoo.user.adapter.out.persistence.command;

import com.hoo.user.adapter.out.persistence.UserMapper;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.api.out.HandleUserEventPort;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.UserAdapterException;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HandleUserEventAdapter implements HandleUserEventPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public void handleBusinessUserCreate(BusinessUserCreateEvent event) {

        UserJpaEntity newBusinessUser = UserJpaEntity.createNewBusinessUser(event.newBusinessUser());
        userJpaRepository.save(newBusinessUser);
    }

    @Override
    public void handleBusinessUserApprove(BusinessUserApproveEvent event) {

        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(event.userID().uuid())
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));
        userJpaEntity.applyEvent(event);
    }
}
