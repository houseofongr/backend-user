package com.hoo.user.adapter.out.persistence.command;

import com.hoo.user.adapter.out.persistence.PersistenceAdapterTest;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.user.domain.User;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import com.hoo.user.domain.vo.UserStatus;
import com.hoo.user.test.domain.UserTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:sql/user.sql")
@PersistenceAdapterTest
class HandleUserEventAdapterTest {

    @Autowired
    HandleUserEventAdapter sut;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("비즈니스 사용자 생성 이벤트 처리")
    void handleCreateBusinessUserEvent() {
        // given
        User user = UserTestData.defaultBusinessUser()
                .withNickname("leaf3")
                .build();
        BusinessUserCreateEvent event = new BusinessUserCreateEvent(user);

        // when
        sut.handleBusinessUserCreate(event);

        // then
        assertThat(userJpaRepository.findByUuid(user.getId().uuid())).isPresent();
    }

    @Test
    @DisplayName("비즈니스 사용자 승인 이벤트 처리")
    void handleApproveBusinessUserEvent() {
        // given
        User user = UserTestData.defaultBusinessUser()
                .withUserID(new User.UserID(userJpaRepository.findUuidById(1L)))
                .build();
        User user2 = UserTestData.defaultBusinessUser()
                .withUserID(new User.UserID(userJpaRepository.findUuidById(2L)))
                .build();
        ZonedDateTime now = ZonedDateTime.now();
        BusinessUserApproveEvent event = user.approve(true);
        BusinessUserApproveEvent event2 = user2.approve(false);

        // when
        sut.handleBusinessUserApprove(event);
        sut.handleBusinessUserApprove(event2);
        UserJpaEntity userJpaEntity = userJpaRepository.findByUuid(user.getId().uuid()).orElseThrow();
        UserJpaEntity userJpaEntity2 = userJpaRepository.findByUuid(user2.getId().uuid()).orElseThrow();

        // then
        assertThat(userJpaEntity.getUserMetadata().getStatus()).isEqualTo(UserStatus.ACTIVATE);
        assertThat(userJpaEntity2.getUserMetadata().getStatus()).isEqualTo(UserStatus.REJECTED);
        assertThat(userJpaEntity.getCommonMetadata().getUpdatedTime()).isAfter(now);
    }
}