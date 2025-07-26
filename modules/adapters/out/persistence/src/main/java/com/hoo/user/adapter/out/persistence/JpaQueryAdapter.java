package com.hoo.user.adapter.out.persistence;

import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.api.out.FindUserInfoPort;
import com.hoo.user.api.out.LoadUserPort;
import com.hoo.user.api.out.QueryUserPort;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.UserAdapterException;
import com.hoo.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class JpaQueryAdapter implements LoadUserPort, QueryUserPort, FindUserInfoPort {

    private final UserJpaRepository userJpaRepository;
    private final PersistenceMapper persistenceMapper;

    @Override
    public User loadBusinessUser(UUID userID) {

        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(userID)
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));

        return persistenceMapper.mapToBusinessUser(userJpaEntity);
    }

    @Override
    public boolean existByName(String nickname) {
        return userJpaRepository.existsByUserMetadata_Nickname(nickname);
    }

    @Override
    public UserInfo findUserInfo(UUID userID) {

        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(userID)
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));

        return persistenceMapper.mapToUserInfo(userJpaEntity);
    }
}
