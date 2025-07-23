package com.hoo.user.adapter.out.persistence.repository;

import com.hoo.user.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUuid(UUID uuid);

    @Query("select u.uuid from UserJpaEntity u where u.id = :id")
    UUID findUuidById(Long id);

    Boolean existsByUserMetadata_Nickname(String nickname);
}
