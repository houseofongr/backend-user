package com.hoo.user.api.in.dto;

public record CreateEmailAuthnCodeResult(
        String message,
        Integer ttl
) {
}
