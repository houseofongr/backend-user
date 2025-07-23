package com.hoo.user.adapter.out.persistence.query;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.adapter.out.persistence.PersistenceAdapterTest;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:sql/user.sql")
@PersistenceAdapterTest
class LoadUserAdapterTest {

    @Autowired
    LoadUserAdapter sut;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("비즈니스 사용자 로드 테스트")
    void loadUser() {
        // given
        UUID userID = userJpaRepository.findUuidById((1L));

        // when
        User user = sut.loadBusinessUser(userID);

        // then
        assertThat(user.getId().uuid()).isEqualTo(userID);
    }
}