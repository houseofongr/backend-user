package com.hoo.user.api.in.web.result;

public record VerifyEmailAuthnCodeResult(
        String message,
        Integer ttl
) {
}
