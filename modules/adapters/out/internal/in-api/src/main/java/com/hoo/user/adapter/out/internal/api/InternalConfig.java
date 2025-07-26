package com.hoo.user.adapter.out.internal.api;

import com.hoo.user.adapter.out.internal.api.auth.AuthWebClientAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

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
                .filter(errorHandlingFilter())
                .build();
    }

    private ExchangeFilterFunction errorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                return clientResponse.bodyToMono(String.class).flatMap(body ->
                        Mono.error(new WebClientResponseException(
                                clientResponse.statusCode().value(),
                                clientResponse.statusCode().toString(),
                                clientResponse.headers().asHttpHeaders(),
                                body.getBytes(StandardCharsets.UTF_8),
                                StandardCharsets.UTF_8
                        ))
                );
            }
            return Mono.just(clientResponse);
        });
    }

}
