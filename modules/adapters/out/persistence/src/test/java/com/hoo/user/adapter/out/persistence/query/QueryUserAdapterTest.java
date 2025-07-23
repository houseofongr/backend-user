package com.hoo.user.adapter.out.persistence.query;

import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.adapter.out.persistence.PersistenceAdapterTest;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.domain.vo.UserStatus;
import com.hoo.user.domain.vo.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:sql/user.sql")
@PersistenceAdapterTest
class QueryUserAdapterTest {

    @Autowired
    QueryUserAdapter sut;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("닉네임 존재여부 확인")
    void existByNickname() {
        // given
        String nickname = "leaf";
        String nickname2 = "notExistUserNickname";

        // when
        assertThat(sut.existByName(nickname)).isTrue();
        assertThat(sut.existByName(nickname2)).isFalse();
    }

    @Test
    @DisplayName("유저정보 조회")
    void findUserInfo() {
        // given
        UUID userID = userJpaRepository.findUuidById(1L);

        // when
        UserInfo userInfo = sut.findUserInfo(userID);

        // then
        assertThat(userInfo.id()).isEqualTo(userID);
        assertThat(userInfo.termsOfUseConsent()).isTrue();
        assertThat(userInfo.personalInfoConsent()).isTrue();
        assertThat(userInfo.email()).isEqualTo("test@example.com");
        assertThat(userInfo.nickname()).isEqualTo("leaf");
        assertThat(userInfo.userType()).isEqualTo(UserType.BUSINESS.name());
        assertThat(userInfo.userStatus()).isEqualTo(UserStatus.NOT_APPROVED.name());
        assertThat(userInfo.registeredAt()).isEqualTo(ZonedDateTime.of(2025,7,19,23,0,0,0, ZoneOffset.UTC).toEpochSecond());
    }
}