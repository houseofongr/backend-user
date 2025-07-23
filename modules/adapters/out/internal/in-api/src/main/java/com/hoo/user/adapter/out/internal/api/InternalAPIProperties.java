package com.hoo.user.adapter.out.internal.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "internal")
public record InternalAPIProperties(
        Auth auth,
        File file
) {

    public record Auth(
            String baseUrl,
            String registerBusinessUserCredential
    ) {
    }

    public record File(
            String baseUrl,
            String uploadFileUrl,
            String getFileInfoUrl,
            String getFileInfoListUrl
    ) {

    }

}
