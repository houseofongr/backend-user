package com.hoo.user.application;

import com.hoo.common.IssueIDPort;
import com.hoo.common.internal.api.auth.RegisterBusinessUserCredentialAPI;
import com.hoo.user.api.in.dto.RegisterBusinessUserCommand;
import com.hoo.user.api.in.dto.RegisterBusinessUserResult;
import com.hoo.user.api.in.RegisterBusinessUserUseCase;
import com.hoo.user.api.out.LoadEmailAuthnPort;
import com.hoo.user.api.out.HandleUserEventPort;
import com.hoo.user.application.exception.ApplicationErrorCode;
import com.hoo.user.application.exception.UserApplicationException;
import com.hoo.user.domain.User;
import com.hoo.user.domain.event.BusinessUserCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterBusinessUserService implements RegisterBusinessUserUseCase {

    private final IssueIDPort issueIDPort;
    private final LoadEmailAuthnPort loadEmailAuthnPort;
    private final HandleUserEventPort handleUserEventPort;
    private final RegisterBusinessUserCredentialAPI registerBusinessUserCredentialAPI;

    @Override
    public RegisterBusinessUserResult create(RegisterBusinessUserCommand command) {

        if (!loadEmailAuthnPort.isAuthenticated(command.email()))
            throw new UserApplicationException(ApplicationErrorCode.UNAUTHORIZED_EMAIL);

        UUID userID = issueIDPort.issueNewID();
        BusinessUserCreateEvent event = User.createBusinessUser(
                new User.UserID(userID),
                command.termsOfUseConsent(),
                command.personalInfoConsent(),
                command.nickname(),
                command.email());

        handleUserEventPort.handleBusinessUserCreate(event);
        registerBusinessUserCredentialAPI.registerBusinessUserCredential(command.email(), command.password());

        User newUser = event.newBusinessUser();

        return new RegisterBusinessUserResult(
                newUser.getId().uuid(),
                newUser.getSensitiveInfo().getEmail(),
                newUser.getUserMetadata().getNickname(),
                newUser.getUserMetadata().getTermsOfUseConsent(),
                newUser.getUserMetadata().getPersonalInfoConsent()
        );
    }
}
