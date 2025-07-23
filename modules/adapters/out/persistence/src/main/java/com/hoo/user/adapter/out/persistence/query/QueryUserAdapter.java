package com.hoo.user.adapter.out.persistence.query;

import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.adapter.out.persistence.UserMapper;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.api.out.FindUserInfoPort;
import com.hoo.user.api.out.QueryUserPort;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.UserAdapterException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class QueryUserAdapter implements QueryUserPort, FindUserInfoPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existByName(String nickname) {
        return userJpaRepository.existsByUserMetadata_Nickname(nickname);
    }

    @Override
    public UserInfo findUserInfo(UUID userID) {

        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(userID)
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));

        return userMapper.mapToUserInfo(userJpaEntity);
    }
}
