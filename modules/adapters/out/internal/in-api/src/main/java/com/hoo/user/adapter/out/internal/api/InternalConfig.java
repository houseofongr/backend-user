package com.hoo.user.adapter.out.internal.api;

import com.hoo.user.adapter.out.internal.api.auth.AuthWebClientAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(InternalProperties.class)
public class InternalConfig {

    @Bean
    public AuthWebClientAdapter saveBusinessUserCredentialWebClientAdapter(
            WebClient authWebClient,
            InternalProperties properties
    ) {

        return new AuthWebClientAdapter(authWebClient, properties);
    }

    @Bean
    public WebClient authWebClient(InternalProperties properties) {

        return WebClient.builder()
                .baseUrl(properties.auth().baseUrl())
                .build();
    }

}
