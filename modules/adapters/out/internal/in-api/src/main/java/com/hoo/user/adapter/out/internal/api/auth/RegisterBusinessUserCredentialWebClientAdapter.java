package com.hoo.user.adapter.out.internal.api.auth;

import com.hoo.common.internal.api.auth.RegisterBusinessUserCredentialAPI;
import com.hoo.user.adapter.out.internal.api.InternalAPIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class RegisterBusinessUserCredentialWebClientAdapter implements RegisterBusinessUserCredentialAPI {

    private final WebClient webClient;
    private final InternalAPIProperties properties;

    @Override
    public void saveBusinessUserPassword(String email, String password) {

        webClient.post()
                .uri(properties.auth().registerBusinessUserCredential())
                .attribute("email", email)
                .attribute("password", password)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
