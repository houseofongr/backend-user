package com.hoo.user.adapter.out.persistence.entity;

import com.hoo.user.domain.vo.SensitiveInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SENSITIVE_INFO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveInfoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String realName;

    @Column
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserJpaEntity user;

    public static SensitiveInfoJpaEntity createNewEntity(SensitiveInfo sensitiveInfo) {

        return new SensitiveInfoJpaEntity(
                null,
                sensitiveInfo.getRealName(),
                sensitiveInfo.getEmail(),
                sensitiveInfo.getPhoneNumber(),
                null
        );
    }

}
