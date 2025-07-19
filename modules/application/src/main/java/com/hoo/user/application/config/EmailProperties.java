package com.hoo.user.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "email")
public record EmailProperties(
        Authn authn
) {

    public record Authn(
            Code code,
            Status status
    ) {

        public record Code(
                Integer ttl
        ) {

        }

        public record Status(
                Integer ttl
        ) {
        }

    }
}
