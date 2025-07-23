package com.hoo.user.adapter.out.persistence.entity.vo;

import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.vo.UserMetadata;
import com.hoo.user.domain.vo.UserStatus;
import com.hoo.user.domain.vo.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserMetadataJpaValue {

    @Column(unique = true)
    private String nickname;

    @Column
    private Boolean termsOfUseConsent;

    @Column
    private Boolean personalInfoConsent;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserType type;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    public static UserMetadataJpaValue from(UserMetadata userMetadata) {

        return new UserMetadataJpaValue(
                userMetadata.getNickname(),
                userMetadata.getTermsOfUseConsent(),
                userMetadata.getPersonalInfoConsent(),
                userMetadata.getType(),
                userMetadata.getStatus()
        );
    }

    public void applyEvent(BusinessUserApproveEvent event) {
        this.status = event.status();
    }
}
