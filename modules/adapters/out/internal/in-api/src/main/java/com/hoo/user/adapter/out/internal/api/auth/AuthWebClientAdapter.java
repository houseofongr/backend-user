package com.hoo.user.adapter.out.internal.api.auth;

import com.hoo.common.internal.api.auth.RegisterBusinessUserCredentialAPI;
import com.hoo.user.adapter.out.internal.api.InternalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class AuthWebClientAdapter implements RegisterBusinessUserCredentialAPI {

    private final WebClient webClient;
    private final InternalProperties properties;

    @Override
    public void registerBusinessUserCredential(String email, String password) {

        webClient.post()
                .uri(properties.auth().registerBusinessUserCredential())
                .body(BodyInserters
                        .fromFormData("email", email)
                        .with("password", password))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
