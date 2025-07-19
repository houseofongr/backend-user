package com.hoo.user.api.out.mail;

public interface SendAuthnCodePort {
    void sendAuthnCode(String emailAddress, String message);
}
