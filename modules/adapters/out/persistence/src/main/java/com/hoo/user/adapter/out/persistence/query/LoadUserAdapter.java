package com.hoo.user.adapter.out.persistence.query;

import com.hoo.user.adapter.out.persistence.UserMapper;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.api.out.LoadUserPort;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.UserAdapterException;
import com.hoo.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class LoadUserAdapter implements LoadUserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User loadBusinessUser(UUID userID) {

        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(userID)
                .orElseThrow(() -> new UserAdapterException(AdapterErrorCode.USER_NOT_FOUND));

        return userMapper.mapToBusinessUser(userJpaEntity);
    }
}
