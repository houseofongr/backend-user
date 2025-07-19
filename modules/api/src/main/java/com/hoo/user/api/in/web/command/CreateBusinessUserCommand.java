package com.hoo.user.api.in.web.command;

public record CreateBusinessUserCommand(
        String email,
        String password,
        String nickname,
        Boolean termsOfUseConsent,
        Boolean personalInfoConsent
) {
}
