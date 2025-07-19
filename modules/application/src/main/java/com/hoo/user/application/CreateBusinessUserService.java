package com.hoo.user.application;

import com.hoo.common.IssueIDPort;
import com.hoo.user.api.in.web.command.CreateBusinessUserCommand;
import com.hoo.user.api.in.web.result.CreateBusinessUserResult;
import com.hoo.user.api.in.web.usecase.CreateBusinessUserUseCase;
import com.hoo.user.api.out.cache.LoadEmailAuthnPort;
import com.hoo.user.api.out.persistence.HandleUserEventPort;
import com.hoo.user.api.out.security.PasswordEncryptPort;
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
public class CreateBusinessUserService implements CreateBusinessUserUseCase {

    private final IssueIDPort issueIDPort;
    private final LoadEmailAuthnPort loadEmailAuthnPort;
    private final PasswordEncryptPort passwordEncryptPort;
    private final HandleUserEventPort handleUserEventPort;

    @Override
    public CreateBusinessUserResult create(CreateBusinessUserCommand command) {

        if (!loadEmailAuthnPort.isAuthenticated(command.email()))
            throw new UserApplicationException(ApplicationErrorCode.UNAUTHORIZED_EMAIL);

        UUID userID = issueIDPort.issueNewID();
        BusinessUserCreateEvent event = User.createBusinessUser(
                new User.UserID(userID),
                command.termsOfUseConsent(),
                command.personalInfoConsent(),
                command.nickname(),
                command.email());

        String encryptPassword = passwordEncryptPort.encrypt(command.password());

        handleUserEventPort.handleUserCreateEvent(event, encryptPassword);
        User newUser = event.newBusinessUser();

        return new CreateBusinessUserResult(
                newUser.getId().uuid(),
                newUser.getSensitiveInfo().getEmail(),
                newUser.getCommonMetadata().getNickname(),
                newUser.getUserMetadata().getTermsOfUseConsent(),
                newUser.getUserMetadata().getPersonalInfoConsent()
        );
    }
}
