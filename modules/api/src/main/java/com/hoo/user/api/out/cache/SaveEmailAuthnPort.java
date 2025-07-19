package com.hoo.user.api.out.cache;

import java.time.Duration;

public interface SaveEmailAuthnPort {
    void saveEmailAuthnCode(String email, String code, Duration ttl);
    void saveAuthenticateStatus(String email, Duration ttl);
}
