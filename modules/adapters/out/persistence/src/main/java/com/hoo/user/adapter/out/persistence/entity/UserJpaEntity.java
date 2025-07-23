package com.hoo.user.adapter.out.persistence.entity;

import com.hoo.user.adapter.out.persistence.entity.vo.CommonMetadataJpaValue;
import com.hoo.user.adapter.out.persistence.entity.vo.UserMetadataJpaValue;
import com.hoo.user.domain.User;
import com.hoo.user.domain.event.BusinessUserApproveEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID uuid;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private SensitiveInfoJpaEntity sensitiveInfo;

    @Embedded
    private UserMetadataJpaValue userMetadata;

    @Embedded
    private CommonMetadataJpaValue commonMetadata;

    public static UserJpaEntity createNewBusinessUser(User businessUser) {

        UserJpaEntity userJpaEntity = new UserJpaEntity(
                null,
                businessUser.getId().uuid(),
                SensitiveInfoJpaEntity.createNewEntity(businessUser.getSensitiveInfo()),
                UserMetadataJpaValue.from(businessUser.getUserMetadata()),
                CommonMetadataJpaValue.from(businessUser.getCommonMetadata())
        );

        userJpaEntity.sensitiveInfo.setUser(userJpaEntity);

        return userJpaEntity;
    }

    public void applyEvent(BusinessUserApproveEvent event) {
        userMetadata.applyEvent(event);
        commonMetadata.applyEvent(event);
    }
}
