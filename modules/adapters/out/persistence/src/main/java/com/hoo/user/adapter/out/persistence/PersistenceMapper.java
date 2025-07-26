package com.hoo.user.adapter.out.persistence;

import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.adapter.out.persistence.entity.SensitiveInfoJpaEntity;
import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.user.adapter.out.persistence.entity.vo.CommonMetadataJpaValue;
import com.hoo.user.adapter.out.persistence.entity.vo.UserMetadataJpaValue;
import com.hoo.user.domain.User;
import com.hoo.user.domain.vo.CommonMetadata;
import com.hoo.user.domain.vo.SensitiveInfo;
import com.hoo.user.domain.vo.UserMetadata;

import java.util.List;

public class PersistenceMapper {

    public User mapToBusinessUser(UserJpaEntity userJpaEntity) {

        return User.load(
                new User.UserID(userJpaEntity.getUuid()),
                mapToSensitiveInfo(userJpaEntity.getSensitiveInfo()),
                mapToUserMetadata(userJpaEntity.getUserMetadata()),
                mapToCommonMetadata(userJpaEntity.getCommonMetadata()),
                List.of()
        );
    }

    private SensitiveInfo mapToSensitiveInfo(SensitiveInfoJpaEntity sensitiveInfo) {

        return new SensitiveInfo(
                sensitiveInfo.getRealName(),
                sensitiveInfo.getEmail(),
                sensitiveInfo.getPhoneNumber()
        );
    }

    private UserMetadata mapToUserMetadata(UserMetadataJpaValue userMetadataJpaValue) {

        return new UserMetadata(
                userMetadataJpaValue.getNickname(),
                userMetadataJpaValue.getTermsOfUseConsent(),
                userMetadataJpaValue.getPersonalInfoConsent(),
                userMetadataJpaValue.getType(),
                userMetadataJpaValue.getStatus()
        );
    }

    private CommonMetadata mapToCommonMetadata(CommonMetadataJpaValue commonMetadataJpaValue) {

        return new CommonMetadata(
                commonMetadataJpaValue.getCreatedTime(),
                commonMetadataJpaValue.getUpdatedTime()
        );
    }

    public UserInfo mapToUserInfo(UserJpaEntity userJpaEntity) {

        return new UserInfo(
                userJpaEntity.getUuid(),
                userJpaEntity.getUserMetadata().getTermsOfUseConsent(),
                userJpaEntity.getUserMetadata().getPersonalInfoConsent(),
                userJpaEntity.getSensitiveInfo().getEmail(),
                userJpaEntity.getUserMetadata().getNickname(),
                userJpaEntity.getUserMetadata().getType().name(),
                userJpaEntity.getUserMetadata().getStatus().name(),
                userJpaEntity.getCommonMetadata().getCreatedTime().toEpochSecond()
        );
    }
}
