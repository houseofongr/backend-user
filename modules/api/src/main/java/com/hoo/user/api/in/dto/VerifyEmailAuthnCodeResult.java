package com.hoo.user.api.in.dto;

public record VerifyEmailAuthnCodeResult(
        String message,
        Integer ttl
) {
}
