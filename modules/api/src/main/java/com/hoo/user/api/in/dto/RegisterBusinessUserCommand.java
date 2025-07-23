package com.hoo.user.api.in.dto;

public record RegisterBusinessUserCommand(
        String email,
        String password,
        String nickname,
        Boolean termsOfUseConsent,
        Boolean personalInfoConsent
) {
}
