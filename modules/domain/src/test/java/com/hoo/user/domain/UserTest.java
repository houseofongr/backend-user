package com.hoo.user.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import com.hoo.user.domain.vo.UserStatus;
import com.hoo.user.domain.vo.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static com.hoo.user.test.domain.UserTestData.defaultBusinessUser;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("비즈니스 사용자 생성")
    void createBusinessUser() {
        // given
        UUID userID = UuidCreator.getTimeOrderedEpoch();

        // when
        BusinessUserCreateEvent event = User.createBusinessUser(new User.UserID(userID), true, true, "leaf", "test@example.com");

        // then
        User user = event.newBusinessUser();
        assertThat(user.getId().uuid()).isEqualTo(userID);
        assertThat(user.getSensitiveInfo().getEmail()).isEqualTo("test@example.com");
        assertThat(user.getUserMetadata().getStatus()).isEqualTo(UserStatus.NOT_APPROVED);
        assertThat(user.getUserMetadata().getType()).isEqualTo(UserType.BUSINESS);
        assertThat(user.getUserMetadata().getTermsOfUseConsent()).isTrue();
        assertThat(user.getUserMetadata().getPersonalInfoConsent()).isTrue();
        assertThat(user.getCommonMetadata().getNickname()).isEqualTo("leaf");
        assertThat(user.getSnsAccounts()).isEmpty();
    }
    
    @Test
    @DisplayName("비즈니스 사용자 승인")
    void approveBusinessUser() {
        // given
        User businessUser = defaultBusinessUser().build();
        ZonedDateTime now = ZonedDateTime.now();

        // when
        BusinessUserApproveEvent event = businessUser.approve();

        // then
        assertThat(event.userID()).isEqualTo(businessUser.getId());
        assertThat(event.approveAt()).isAfter(now);
    }

}