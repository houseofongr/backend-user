package com.hoo.user.api.out;

import com.hoo.user.domain.User;

import java.util.UUID;

public interface LoadUserPort {
    User loadBusinessUser(UUID userID);
}
