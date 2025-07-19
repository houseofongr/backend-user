package com.hoo.user.domain;

import com.hoo.user.domain.User.UserID;
import com.hoo.user.domain.vo.CommonMetadata;
import com.hoo.user.domain.vo.SnsMetadata;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class SnsAccount {

    private final SnsAccountID id;
    private final UserID userID;
    private final SnsMetadata snsMetadata;
    private final CommonMetadata commonMetadata;

    public record SnsAccountID(UUID uuid) {
    }
}
