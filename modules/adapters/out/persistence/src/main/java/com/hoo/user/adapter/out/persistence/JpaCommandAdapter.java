package com.hoo.user.adapter.out.persistence;

import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.api.out.SaveUserPort;
import com.hoo.user.api.out.UpdateUserStatusPort;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.UserAdapterException;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaCommandAdapter implements SaveUserPort, UpdateUserStatusPort {

    private final UserJpaRepository userJpaRepository;
    private final PersistenceMapper persistenceMapper;

    @Override
    public void saveBusinessUser(BusinessUserCreateEvent event) {
        if (userJpaRepository.existsByUserMetadata_Nickname(event.newBusinessUser().getUserMetadata().getNickname()))
            throw new UserAdapterException(AdapterErrorCode.USER_NAME_DUPLICATE);
        UserJpaEntity newBusinessUser = UserJpaEntity.createNewBusinessUser(event.newBusinessUser());
        userJpaRepository.save(newBusinessUser);
    }

    @Override
    public void updateBusinessUserApprove(BusinessUserApproveEvent event) {
        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(event.userID().uuid())
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));
        userJpaEntity.applyEvent(event);
    }
}
