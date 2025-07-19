package com.hoo.user.api.out.cache;

public interface LoadEmailAuthnPort {
    String loadEmailAuthnCode(String email);
    boolean isAuthenticated(String email);
}
