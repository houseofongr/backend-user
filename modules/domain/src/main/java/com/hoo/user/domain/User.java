package com.hoo.user.domain;

import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import com.hoo.user.domain.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class User {

    private final UserID id;
    private final SensitiveInfo sensitiveInfo;
    private UserMetadata userMetadata;
    private CommonMetadata commonMetadata;
    private final List<SnsAccount> snsAccounts;

    public static BusinessUserCreateEvent createBusinessUser(UserID userID, Boolean termsOfUseConsent, Boolean personalInfoConsent, String nickname, String email) {

        return new BusinessUserCreateEvent(
                new User(userID,
                        new SensitiveInfo(null, email, null),
                        new UserMetadata(nickname, termsOfUseConsent, personalInfoConsent, UserType.BUSINESS, UserStatus.NOT_APPROVED),
                        new CommonMetadata(ZonedDateTime.now(), ZonedDateTime.now()),
                        List.of())
        );
    }

    public static User load(UserID id, SensitiveInfo sensitiveInfo, UserMetadata userMetadata, CommonMetadata commonMetadata, List<SnsAccount> snsAccounts) {
        return new User(id, sensitiveInfo, userMetadata, commonMetadata, snsAccounts);
    }

    public BusinessUserApproveEvent approve(Boolean approve) {

        this.userMetadata = approve? userMetadata.approve() : userMetadata.reject();
        this.commonMetadata = commonMetadata.update();

        return new BusinessUserApproveEvent(id, userMetadata.getStatus(), commonMetadata.getUpdatedTime());
    }

    public record UserID(UUID uuid) {
    }
}
