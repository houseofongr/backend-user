package com.hoo.user.api.out.security;

public interface PasswordEncryptPort {
    String encrypt(String password);
    boolean match(String originalPassword, String encryptedPassword);
}
